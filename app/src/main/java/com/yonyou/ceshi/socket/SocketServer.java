package com.yonyou.ceshi.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("接受之前");
        Socket socket =serverSocket.accept();
        System.out.println("接受之后......");
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String msg;
        while((msg = br.readLine()) != null){
            System.out.println("客户端发送的信息:  "+msg);
        }
        socket.close();
    }
}
