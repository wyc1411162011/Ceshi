package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yonyou.service.MyService;

public class ServiceDemoActivity extends BaseActivity {
    private Button bt_start;
    private Button bt_stop;
    private Button bt_bind;
    private Button bt_unbind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        bindViews();
        bindLisenters();
    }
    private void bindViews() {

        bt_start = (Button) findViewById(R.id.bt_start);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        bt_bind = (Button) findViewById(R.id.bt_bind);
        bt_unbind = (Button) findViewById(R.id.bt_unbind);
    }
    private void bindLisenters(){
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyService.class);
                startService(intent);
            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyService.class);
                stopService(intent);
            }
        });
    }
}