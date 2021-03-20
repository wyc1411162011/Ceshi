package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yonyou.reciever.MyBroadCastReciver;

public class BroadcastReciverActivity extends Activity {
    private Button bt_register;
    private Button bt_send;
    private String ACTION="com.wyc.register";
    private MyBroadCastReciver myBroadCastReciver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_reciver);
        myBroadCastReciver = new MyBroadCastReciver();
        bindViews();
        bindListeners();
    }
    private void bindViews(){
        bt_register = (Button)findViewById(R.id.bt_register);
        bt_send= (Button)findViewById(R.id.bt_send);
    }
    private void bindListeners(){
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ACTION);
                registerReceiver(myBroadCastReciver,intentFilter);
            }
        });
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction(ACTION);
                intent.putExtra("data","123哈哈");
                sendBroadcast(intent);
            }
        });
    }
}