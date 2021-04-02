package com.yonyou.ceshi.socket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.yonyou.ceshi.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketActivity extends Activity {
    private Button bt_send;
    private EditText et;
    private TextView tv_result;
    private BufferedReader br;
    private PrintWriter pw;
    private String sendResult;
    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue();
    private  Socket socket;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            tv_result.setText(tv_result.getText().toString()+msg.obj.toString());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        bt_send = (Button)findViewById(R.id.bt_send);
        et=(EditText)findViewById(R.id.et);
        tv_result =(TextView)findViewById(R.id.tv_result);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                     socket = new Socket("192.168.43.6",12345);
                    socket.setTcpNoDelay(true);
                    socket.setKeepAlive(true);
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(new WriteRunable()).start();
        new Thread(new ReadRunable()).start();
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et.getText().toString()+"\n";
                if(!TextUtils.isEmpty(text)){
                    try {
                        queue.put(text);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    class WriteRunable implements Runnable{
        @Override
        public void run() {
            try{
                while ((sendResult=queue.take())!= null){
                    if(socket != null &&socket.isConnected() && !socket.isOutputShutdown()&& pw!=null){
                        pw.write(sendResult);
                        pw.flush();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    class ReadRunable implements Runnable{
        @Override
        public void run() {
            while(true){
                if(socket != null &&socket.isConnected() && !socket.isInputShutdown() && br!=null){
                    try {
                        Log.e("tag","读取中...");
                        String reulst = br.readLine();
                        Log.e("tag","读取后"+reulst);
                        if(!TextUtils.isEmpty(reulst)){
                            Message message = Message.obtain();
                            message.obj = reulst+"\n";
                            handler.sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
