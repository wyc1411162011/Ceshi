package com.yonyou.ceshi.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yonyou.ceshi.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdpSocketActivity extends AppCompatActivity {
    EditText mEditText;
    Button mButton;

    InetAddress mInetAddress;
    int mPort = 12345;

    DatagramSocket mUdpSocket;
    private ExecutorService mExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udp_socket);

        mEditText = findViewById(R.id.et_input);
        mButton = findViewById(R.id.bt_send);

        initUdpClient();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditText.getText().toString().isEmpty()) {
                    sendToServer(mEditText.getText().toString());
                }
            }
        });
    }

    private void initUdpClient() {
        try {
            mInetAddress = InetAddress.getByName("192.168.43.6");
            mUdpSocket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendToServer(String strToSend) {
        //数据包里面有ip 端口号这些
        DatagramPacket datagramPacketToSend = new DatagramPacket(strToSend.getBytes(), strToSend.getBytes().length, mInetAddress, mPort);

        mExecutorService = Executors.newCachedThreadPool();
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mUdpSocket.send(datagramPacketToSend);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mExecutorService.shutdown();

    }
}