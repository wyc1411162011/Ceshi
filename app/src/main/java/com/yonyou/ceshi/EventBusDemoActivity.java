package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_demo);
        findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        EventBus.getDefault().register(EventBusDemoActivity.this);
        findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<100;i++){
                    EventBus.getDefault().post(new Event());
                }

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().unregister(this);
    }
    //如果没有指定线程则使用，哪个线程发的事件  接受的就用哪个线程
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void recieverMessage(Event messageEvent) {
        Log.e("tag","接收消息 "+"线程:"+Thread.currentThread().getName());
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void mainThreadReciever(Event messageEvent) {
        Log.e("tag","back 接受  "+"线程:"+Thread.currentThread().getName());
    }
//    @Subscribe(threadMode = ThreadMode.ASYNC)
//    public void asyncThreadReceive(Event messageEvent) {
//        Log.e("tag","asyncThreadReceive 接收"+"线程:"+Thread.currentThread().getName());
//    }
    public class Event{

    }





}