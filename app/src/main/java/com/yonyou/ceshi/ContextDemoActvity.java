package com.yonyou.ceshi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.yonyou.kotlin.seven.Base;

import java.util.ArrayList;
import java.util.List;

public class ContextDemoActvity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_demo_actvity);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("tag",getClass().getSimpleName()+" ___"+"onConfigurationChanged");
    }

    public static void main(String[] args) {
        int mGroupFlags =0;
        int FLAG_DISALLOW_INTERCEPT = 0x80000;
        mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
        final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
        System.out.println(disallowIntercept);
    }
}