package com.yonyou.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;

import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2020-10-31
 * describle:
 */
public class MyIntentService extends IntentService {
    private int i = 0;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }
    public MyIntentService() {
        super("我的service");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

//        i++;
//        Log.e("tag","MyIntentService 的线程的名字"+ Thread.currentThread().getName()+" "+i);
//        try {
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            Field field = getClass().getDeclaredField("mServiceLooper");
//            field.setAccessible(true);
//           Object o= field.get(this);
//            Log.e("tag",o+"  --");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("tag","onBind   "+Thread.currentThread().getName());
        return null;
    }

    @Override
    public void onCreate() {
        Field filearr[]=getClass().getDeclaredFields();
        if(filearr != null){
            for(Field field:filearr){
                field.setAccessible(true);
                try {
                    Object o=field.get(this);
                    Log.e("tag","on  ------------------------------------ "+o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.e("tag","onCreate   "+Thread.currentThread().getName());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("tag","onStartCommand   "+Thread.currentThread().getName()+"  startId "+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("tag","onDestroy    "+Thread.currentThread().getName());
        super.onDestroy();
    }
}
