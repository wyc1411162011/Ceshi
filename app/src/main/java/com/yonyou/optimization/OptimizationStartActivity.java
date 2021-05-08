package com.yonyou.optimization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;
//https://www.jianshu.com/p/a1425e733910
public class OptimizationStartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimization_start);
        findViewById(R.id.bt_use_traceview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAdd(3, 5);
            }
        });
    }
    private void testAdd(int a, int b) {
        //Debug.startMethodTracing("app_trace");
        int c = a + b;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("Restart", "c =  a + b = " + c);
        //Debug.stopMethodTracing();

        //帧动画消耗资源比较多

    }
}