/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package okhttp3.internal.connection;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;

import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

import static okhttp3.internal.Util.closeQuietly;

/**
 * This class coordinates the relationship between three entities:
 * <p>
 * <ul>
 * <li><strong>Connections:</strong> physical socket connections to remote servers. These are
 * potentially slow to establish so it is necessary to be able to cancel a connection
 * currently being connected.
 * <li><strong>Streams:</strong> logical HTTP request/response pairs that are layered on
 * connections. Each connection has its own allocation limit, which defines how many
 * concurrent streams that connection can carry. HTTP/1.x connections can carry 1 stream
 * at a time, HTTP/2 typically carry multiple.
 * <li><strong>Calls:</strong> a logical sequence of streams, typically an initial request and
 * its follow up requests. We prefer to keep all streams of a single call on the same
 * connection for better behavior and locality.
 * </ul>
 * <p>
 * <p>Instances of this class act on behalf of the call, using one or more streams over one or more
 * connections. This class has APIs to release each of the above resources:
 * <p>
 * <ul>
 * <li>{@link #noNewStreams()} prevents the connection from being used for new streams in the
 * future. Use this after a {@code Connection: close} header, or when the connection may be
 * inconsistent.
 * <li>{@link #streamFinished streamFinished()} releases the active stream from this allocation.
 * Note that only one stream may be active at a given time, so it is necessary to call
 * {@link #streamFinished streamFinished()} before creating a subsequent stream with {@link
 * #newStream newStream()}.
 * <li>{@link #release()} removes the call's hold on the connection. Note that this won't
 * immediately free the connection if there is a stream still lingering. That happens when a
 * call is complete but its response body has yet to be fully consumed.
 * </ul>
 * <p>
 * <p>This class supports {@linkplain #cancel asynchronous canceling}. This is intended to have the
 * smallest blast radius possible. If an HTTP/2 stream is active, canceling will cancel that stream
 * but not the other streams sharing its connection. But if the TLS handshake is still in progress
 * then canceling may break the entire connection.
 */
public final class StreamAllocation {
    public final Address address;
    private RouteSelector.Selection routeSelection;
    private Route route;
    private final ConnectionPool connectionPool;
    public final Call call;
    public final EventListener eventListener;
    private final Object callStackTrace;

    // State guarded by connectionPool.
    private final RouteSelector routeSelector;
    private int refusedStreamCount;
    private RealConnection connection;
    private boolean reportedAcquired;
    private boolean released;
    private boolean canceled;
    private HttpCodec codec;

    public StreamAllocation(ConnectionPool connectionPool, Address address, Call call,
                            EventListener eventListener, Object callStackTrace) {
        this.connectionPool = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
        this.routeSelector = new RouteSelector(address, routeDatabase(), call, eventListener);
        this.callStackTrace = callStackTrace;
    }

    public HttpCodec newStream(
            OkHttpClient client, Interceptor.Chain chain, boolean doExtensiveHealthChecks) {
        int connectTimeout = chain.connectTimeoutMillis();
        int readTimeout = chain.readTimeoutMillis();
        int writeTimeout = chain.writeTimeoutMillis();
        int pingIntervalMillis = client.pingIntervalMillis();
        boolean connectionRetryEnabled = client.retryOnConnectionFailure();

        try {
            //TODO RealConnection 对Socket连接的封装
            //TODO TPC/IP协议是传输层协议，主要解决数据如何在网络中传输
            //TODO Socket则是对TCP/IP协议的封装和应用(程序员层面上)。
            //TODO Http 应用层协议,解决如何包装数据
            //TODO 使用Http协议封装数据，借助TCP/IP协议的实现:Socket 进行数据传输
            RealConnection resultConnection = findHealthyConnection(connectTimeout, readTimeout,
                    writeTimeout, pingIntervalMillis, connectionRetryEnabled,
                    doExtensiveHealthChecks);
            //TODO HttpCodec 处理解析请求与响应的工具类
            HttpCodec resultCodec = resultConnection.newCodec(client, chain, this);

            synchronized (connectionPool) {
                codec = resultCodec;
                return resultCodec;
            }
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    /**
     * Finds a connection and returns it if it is healthy. If it is unhealthy the process is
     * repeated
     * until a healthy connection is found.
     * TODO 查找连接并在健康状况下返回。 如果不健康，则重复该过程直到找到健康的连接一直没找到，最终创建新的socket连接。
     */
    private RealConnection findHealthyConnection(int connectTimeout, int readTimeout,
                                                 int writeTimeout, int pingIntervalMillis,
                                                 boolean connectionRetryEnabled,
                                                 boolean doExtensiveHealthChecks) throws
            IOException {
        while (true) {
            //TODO 真正找连接的方法
            RealConnection candidate = findConnection(connectTimeout, readTimeout, writeTimeout,
                    pingIntervalMillis, connectionRetryEnabled);

            // If this is a brand new connection, we can skip the extensive health checks.
            synchronized (connectionPool) {
                if (candidate.successCount == 0) {
                    return candidate;
                }
            }

            // Do a (potentially slow) check to confirm that the pooled connection is still good.
            // If it
            // isn't, take it out of the pool and start again.
            if (!candidate.isHealthy(doExtensiveHealthChecks)) {
                noNewStreams();
                continue;
            }

            return candidate;
        }
    }

    /**
     * Returns a connection to host a new stream. This prefers the existing connection if it exists,
     * then the pool, finally building a new connection.
     */
    private RealConnection findConnection(int connectTimeout, int readTimeout, int writeTimeout,
                                          int pingIntervalMillis, boolean connectionRetryEnabled)
            throws IOException {
        boolean foundPooledConnection = false;
        RealConnection result = null;
        Route selectedRoute = null;
        Connection releasedConnection;
        Socket toClose;
        synchronized (connectionPool) {
            if (released) throw new IllegalStateException("released");
            if (codec != null) throw new IllegalStateException("codec != null");
            if (canceled) throw new IOException("Canceled");

            // Attempt to use an already-allocated connection. We need to be careful here because
            // our already-allocated connection may have been restricted from creating new streams.
            releasedConnection = this.connection;
            toClose = releaseIfNoNewStreams();
            if (this.connection != null) {
                // We had an already-allocated connection and it's good.
                result = this.connection;
                releasedConnection = null;
            }
            if (!reportedAcquired) {
                // If the connection was never reported acquired, don't report it as released!
                releasedConnection = null;
            }
            //TODO 从连接池中获取连接 (先看put加入连接池)
            if (result == null) {
                // Attempt to get a connection from the pool.
                Internal.instance.get(connectionPool, address, this, null);
                if (connection != null) {
                    foundPooledConnection = true;
                    result = connection;
                } else {
                    selectedRoute = route;
                }
            }
        }
        closeQuietly(toClose);

        if (releasedConnection != null) {
            eventListener.connectionReleased(call, releasedConnection);
        }
        if (foundPooledConnection) {
            eventListener.connectionAcquired(call, result);
        }
        //TODO 连接可用就返回 否则需要创建新的连接
        if (result != null) {
            // If we found an already-allocated or pooled connection, we're done.
            return result;
        }
        // If we need a route selection, make one. This is a blocking operation.
        //TODO 路由选择器
        //TODO 有多个ip地址的主机,比如高吞吐量的web服务器（服务器集群）
        //TODO 默认实现为 InetAddress.getAllByName(hostname)
        //TODO 比如  InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
        //TODO 将会获得[www.baidu.com/14.215.177.39,www.baidu.com/14.215.177.38]
        //TODO 实际上这里是 从连接池查看 是否有14.215.177.39的闲置连接，没有则查找下一个 14.215.177.38
        //TODO 如果都没有会创建一个 RealConnection 里面包含了 所有的14.215.177.39和14.215.177.38
        boolean newRouteSelection = false;
        if (selectedRoute == null && (routeSelection == null || !routeSelection.hasNext())) {
            newRouteSelection = true;
            routeSelection = routeSelector.next();
        }

        synchronized (connectionPool) {
            if (canceled) throw new IOException("Canceled");

            if (newRouteSelection) {
                // Now that we have a set of IP addresses, make another attempt at getting a
                // connection from
                // the pool. This could match due to connection coalescing.
                List<Route> routes = routeSelection.getAll();
                for (int i = 0, size = routes.size(); i < size; i++) {
                    Route route = routes.get(i);
                    Internal.instance.get(connectionPool, address, this, route);
                    if (connection != null) {
                        foundPooledConnection = true;
                        result = connection;
                        this.route = route;
                        break;
                    }
                }
            }

            if (!foundPooledConnection) {
                if (selectedRoute == null) {
                    selectedRoute = routeSelection.next();
                }

                // Create a connection and assign it to this allocation immediately. This makes
                // it possible
                // for an asynchronous cancel() to interrupt the handshake we're about to do.
                route = selectedRoute;
                refusedStreamCount = 0;
                //TODO 创建新的连接
                result = new RealConnection(connectionPool, selectedRoute);
                //TODO 使用弱引用进行引用计数
                acquire(result, false);
            }
        }

        // If we found a pooled connection on the 2nd time around, we're done.
        if (foundPooledConnection) {
            eventListener.connectionAcquired(call, result);
            return result;
        }
        //TODO 执行连接
        // Do TCP + TLS handshakes. This is a blocking operation.
        result.connect(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis,
                connectionRetryEnabled, call, eventListener);
        routeDatabase().connected(result.route());

        Socket socket = null;
        synchronized (connectionPool) {
            reportedAcquired = true;
            //TODO 加入连接池
            // Pool the connection.
            Internal.instance.put(connectionPool, result);

            // If another multiplexed connection to the same address was created concurrently, then
            // release this connection and acquire that one.
            if (result.isMultiplexed()) {
                socket = Internal.instance.deduplicate(connectionPool, address, this);
                result = connection;
            }
        }
        closeQuietly(socket);

        eventListener.connectionAcquired(call, result);
        return result;
    }

    /**
     * Releases the currently held connection and returns a socket to close if the held connection
     * restricts new streams from being created. With HTTP/2 multiple requests share the same
     * connection so it's possible that our connection is restricted from creating new streams
     * during
     * a follow-up request.
     */
    private Socket releaseIfNoNewStreams() {
        assert (Thread.holdsLock(connectionPool));
        RealConnection allocatedConnection = this.connection;
        if (allocatedConnection != null && allocatedConnection.noNewStreams) {
            return deallocate(false, false, true);
        }
        return null;
    }

    public void streamFinished(boolean noNewStreams, HttpCodec codec, long bytesRead, IOException
            e) {
        eventListener.responseBodyEnd(call, bytesRead);

        Socket socket;
        Connection releasedConnection;
        boolean callEnd;
        synchronized (connectionPool) {
            if (codec == null || codec != this.codec) {
                throw new IllegalStateException("expected " + this.codec + " but was " + codec);
            }
            if (!noNewStreams) {
                connection.successCount++;
            }
            releasedConnection = connection;
            socket = deallocate(noNewStreams, false, true);
            if (connection != null) releasedConnection = null;
            callEnd = this.released;
        }
        closeQuietly(socket);
        if (releasedConnection != null) {
            eventListener.connectionReleased(call, releasedConnection);
        }

        if (e != null) {
            eventListener.callFailed(call, e);
        } else if (callEnd) {
            eventListener.callEnd(call);
        }
    }

    public HttpCodec codec() {
        synchronized (connectionPool) {
            return codec;
        }
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(connectionPool);
    }

    public Route route() {
        return route;
    }

    public synchronized RealConnection connection() {
        return connection;
    }

    public void release() {
        Socket socket;
        Connection releasedConnection;
        synchronized (connectionPool) {
            releasedConnection = connection;
            socket = deallocate(false, true, false);
            if (connection != null) releasedConnection = null;
        }
        closeQuietly(socket);
        if (releasedConnection != null) {
            eventListener.connectionReleased(call, releasedConnection);
        }
    }

    /**
     * Forbid new streams from being created on the connection that hosts this allocation.
     */
    public void noNewStreams() {
        Socket socket;
        Connection releasedConnection;
        synchronized (connectionPool) {
            releasedConnection = connection;
            socket = deallocate(true, false, false);
            if (connection != null) releasedConnection = null;
        }
        closeQuietly(socket);
        if (releasedConnection != null) {
            eventListener.connectionReleased(call, releasedConnection);
        }
    }

    /**
     * Releases resources held by this allocation. If sufficient resources are allocated, the
     * connection will be detached or closed. Callers must be synchronized on the connection pool.
     * <p>
     * <p>Returns a closeable that the caller should pass to {@link Util#closeQuietly} upon
     * completion
     * of the synchronized block. (We don't do I/O while synchronized on the connection pool.)
     */
    private Socket deallocate(boolean noNewStreams, boolean released, boolean streamFinished) {
        assert (Thread.holdsLock(connectionPool));

        if (streamFinished) {
            this.codec = null;
        }
        if (released) {
            this.released = true;
        }
        Socket socket = null;
        if (connection != null) {
            if (noNewStreams) {
                connection.noNewStreams = true;
            }
            if (this.codec == null && (this.released || connection.noNewStreams)) {
                release(connection);
                if (connection.allocations.isEmpty()) {
                    connection.idleAtNanos = System.nanoTime();
                    if (Internal.instance.connectionBecameIdle(connectionPool, connection)) {
                        socket = connection.socket();
                    }
                }
                connection = null;
            }
        }
        return socket;
    }

    public void cancel() {
        HttpCodec codecToCancel;
        RealConnection connectionToCancel;
        synchronized (connectionPool) {
            canceled = true;
            codecToCancel = codec;
            connectionToCancel = connection;
        }
        if (codecToCancel != null) {
            codecToCancel.cancel();
        } else if (connectionToCancel != null) {
            connectionToCancel.cancel();
        }
    }

    public void streamFailed(IOException e) {
        Socket socket;
        Connection releasedConnection;
        boolean noNewStreams = false;

        synchronized (connectionPool) {
            if (e instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) e;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    refusedStreamCount++;
                }
                // On HTTP/2 stream errors, retry REFUSED_STREAM errors once on the same
                // connection. All
                // other errors must be retried on a new connection.
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM ||
                        refusedStreamCount > 1) {
                    noNewStreams = true;
                    route = null;
                }
            } else if (connection != null
                    && (!connection.isMultiplexed() || e instanceof ConnectionShutdownException)) {
                noNewStreams = true;

                // If this route hasn't completed a call, avoid it for new connections.
                if (connection.successCount == 0) {
                    if (route != null && e != null) {
                        routeSelector.connectFailed(route, e);
                    }
                    route = null;
                }
            }
            releasedConnection = connection;
            socket = deallocate(noNewStreams, false, true);
            if (connection != null || !reportedAcquired) releasedConnection = null;
        }

        closeQuietly(socket);
        if (releasedConnection != null) {
            eventListener.connectionReleased(call, releasedConnection);
        }
    }

    /**
     * Use this allocation to hold {@code connection}. Each call to this must be paired with a
     * call to
     * {@link #release} on the same connection.
     */
    public void acquire(RealConnection connection, boolean reportedAcquired) {
        assert (Thread.holdsLock(connectionPool));
        if (this.connection != null) throw new IllegalStateException();

        this.connection = connection;
        this.reportedAcquired = reportedAcquired;
        connection.allocations.add(new StreamAllocationReference(this, callStackTrace));
    }

    /**
     * Remove this allocation from the connection's list of allocations.
     */
    private void release(RealConnection connection) {
        for (int i = 0, size = connection.allocations.size(); i < size; i++) {
            Reference<StreamAllocation> reference = connection.allocations.get(i);
            if (reference.get() == this) {
                connection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    /**
     * Release the connection held by this connection and acquire {@code newConnection} instead.
     * It is
     * only safe to call this if the held connection is newly connected but duplicated by {@code
     * newConnection}. Typically this occurs when concurrently connecting to an HTTP/2 webserver.
     * <p>
     * <p>Returns a closeable that the caller should pass to {@link Util#closeQuietly} upon
     * completion
     * of the synchronized block. (We don't do I/O while synchronized on the connection pool.)
     */
    public Socket releaseAndAcquire(RealConnection newConnection) {
        assert (Thread.holdsLock(connectionPool));
        if (codec != null || connection.allocations.size() != 1) throw new IllegalStateException();

        // Release the old connection.
        Reference<StreamAllocation> onlyAllocation = connection.allocations.get(0);
        Socket socket = deallocate(true, false, false);

        // Acquire the new connection.
        this.connection = newConnection;
        newConnection.allocations.add(onlyAllocation);

        return socket;
    }

    public boolean hasMoreRoutes() {
        return route != null
                || (routeSelection != null && routeSelection.hasNext())
                || routeSelector.hasNext();
    }

    @Override
    public String toString() {
        RealConnection connection = connection();
        return connection != null ? connection.toString() : address.toString();
    }

    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        /**
         * Captures the stack trace at the time the Call is executed or enqueued. This is helpful
         * for
         * identifying the origin of connection leaks.
         */
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation referent, Object callStackTrace) {
            super(referent);
            this.callStackTrace = callStackTrace;
        }
    }
}
