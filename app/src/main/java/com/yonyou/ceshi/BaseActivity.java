package com.yonyou.ceshi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.yonyou.tool.Util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2020-11-10
 * describle:
 */
public class BaseActivity extends Activity {
    private boolean isPrintLifeCycle =false;
    public Context context;
    public void setPrintLifeCycle (boolean isPrintLifeCycle){
        this.isPrintLifeCycle = isPrintLifeCycle;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if(isPrintLifeCycle){
            Util.print(getClass(),"");
        }
        super.onConfigurationChanged(newConfig);
    }
}
