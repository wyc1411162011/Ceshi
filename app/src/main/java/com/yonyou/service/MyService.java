package com.yonyou.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.yonyou.ceshi.activitymode.StandardAActivity;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("tag","onBind   "+Thread.currentThread().getName());
        return new SimpleBinder();
    }

    @Override
    public void onCreate() {
        Log.e("tag","onCreate   "+Thread.currentThread().getName());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("tag","onStartCommand   "+Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("tag","onDestroy    "+Thread.currentThread().getName());
        super.onDestroy();
    }
    class SimpleBinder extends Binder {

        public void doTask() {
            Log.d("tag", "doTask");
        }
    }
}