package com.wyc.base;

import android.app.Application;
import android.util.Log;

/**
 * Created by ufsoft on2021/4/28
 * describle:
 */
public class BaseMoudleAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("tag","BaseMoudleAppliction模块的"+" onCreate");
    }
}
