package com.yonyou;

import android.app.Application;
import android.util.Log;

import com.yonyou.tool.Util;

import java.io.Serializable;

/**
 * Created by ufsoft on2020-11-24
 * describle:
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String name = new String ("name");

    }
}

