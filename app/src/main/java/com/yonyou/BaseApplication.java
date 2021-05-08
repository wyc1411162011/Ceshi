package com.yonyou;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.yonyou.tool.Util;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ufsoft on2020-11-24
 * describle:
 */
public class BaseApplication extends Application {
    private CountDownLatch mCountDownLatch = new CountDownLatch(1);

    @Override
    public void onCreate() {
        super.onCreate();
        String processName=Util.getCurrentProcessName(this);
        if (processName.equals(getPackageName())) {
            Log.e("tag","项目入口的进程");
        }else{
            Log.e("tag","三方开的进程");
        }
        new Thread("初始化"){
            @Override
            public void run() {
                super.run();
                //initSetting();
                mCountDownLatch.countDown();
            }
        }.start();
        Util.number++;

        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("tag","执行完子线程的设置");
    }
    private void initSetting(){
        //模仿一下设置大量的数据怎么办
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Util.print(getClass(),"-----");
    }


}

