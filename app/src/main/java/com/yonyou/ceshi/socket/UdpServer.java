package com.yonyou.ceshi.socket;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class UdpServer {

    public static void main(String[] args) {
        try {
            //ip地址
            InetAddress mInetAddress = InetAddress.getLocalHost();
            System.out.println("server address:"+mInetAddress.getHostAddress());
            //端口号
            int mPort=12345;
            // ip+port 打开server udpSocket
            DatagramSocket udpSocketServer = new DatagramSocket(mPort, mInetAddress);
            ExecutorService executorService = new ScheduledThreadPoolExecutor(2);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        //接收缓冲区
                        byte[] buf = new byte[1024];
                        System.out.println("接收之前");
                        //接收包
                        DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
                        try {
                            //通过socket将接收内容置入 接收包 中
                            udpSocketServer.receive(receivedPacket);
                            System.out.println("接收ip："+receivedPacket.getAddress());
                            System.out.println("接收端口port:" + receivedPacket.getPort());
                            System.out.println("接收内容：" + new String(receivedPacket.getData(), 0, receivedPacket.getLength()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}