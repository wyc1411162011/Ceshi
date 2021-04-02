package com.yonyou.ceshi.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ChatServer {
    private List<Socket>socketList = new ArrayList<>();
    private ExecutorService executor;

    public static void main(String[] args) {
//
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(address.getCanonicalHostName());//主机别名
        System.out.println(address.getHostAddress());//获取IP地址

        new ChatServer();
    }
    public ChatServer(){

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            while(true){
                System.out.println("接收之前");
                Socket socket = serverSocket.accept();
                socketList.add(socket);
                System.out.println("接收之后.....");
                executor=Executors.newCachedThreadPool();
                executor.execute(new MyRunable(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class MyRunable implements Runnable{
        private Socket socket;
        private BufferedReader br;
        private PrintWriter pw;
        private String result;
        public MyRunable(Socket socket){
            this.socket = socket;
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private  void sendMsg(){
            System.out.println("服务器发送前");
           pw.write("收到的第"+socketList.size()+"个连接"+"\n");
           pw.flush();
           System.out.println("服务器发送完毕....");
        }
        @Override
        public void run() {
            System.out.println("run方法"+Thread.currentThread().getName());
            while(true){
                try {
                    if((result = br.readLine())!=null){
                        System.out.println("接收到的  "+result);
                        sendMsg();
                        if(result.equals("bye")){
                            socket.close();
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.toString());
                    e.printStackTrace();
                }
            }
        }
    }


//    private ServerSocket server = null; //服务器的ServerSocket
//    private static final int PORT = 12345;
//    private List<Socket> mClients = new ArrayList<>();
//    private ExecutorService mExec = null;
//
//    public static void main(String ... args){
//        new ChatServer();
//    }
//
//    public ChatServer(){
//        //开启服务
//        System.out.println("服务器运行中。。。");
//        try {
//            server = new ServerSocket(PORT);
//            InetAddress inetAddress = InetAddress.getLocalHost();
//            System.out.println("ip: " + inetAddress.getHostAddress());
//            //创建一个线程池
//            mExec = Executors.newCachedThreadPool();
//            Socket client = null;
//            while (true){
//                client = server.accept();
//                mClients.add(client);
//                mExec.execute(new Service(client));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    class Service implements Runnable{
//        private Socket socket;
//
//        private BufferedReader br = null;
//        PrintWriter pw;
//        private String msg = "";
//
//        public Service(Socket socket){
//            this.socket = socket;
//            try {
//                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                 pw = new PrintWriter(socket.getOutputStream());//将输出流包装成打印流
//                //this.sendMsg();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public void sendMsg(){//会给客户端的消息
//            System.out.println("发送中......................");
//            int num = mClients.size();
//            try {
//                pw.write("你好 你是第"+ num + "个客户");
//                pw.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    if ((msg = br.readLine()) != null) {
//                        System.out.println("客户端说：" + msg);
//                        sendMsg();
//                        if ("bye".equals(msg)) {//应用自己定义的协议
//                            socket.close();
//                            break;
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
