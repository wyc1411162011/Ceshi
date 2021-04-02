package com.yonyou.ceshi.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",12345);
        OutputStream os=socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(os);
        printWriter.write("好的---");
        printWriter.flush();
        socket.close();
    }
}
