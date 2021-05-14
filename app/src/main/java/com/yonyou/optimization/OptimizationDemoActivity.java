package com.yonyou.optimization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;
import com.yonyou.tool.Util;

import java.util.concurrent.locks.ReentrantLock;

public class OptimizationDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimization_demo);
        Log.e("tag","第2个进程的 "+ Util.number);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
       String name= sharedPreferences.getString("name","");
       Log.e("tag",name);
        sharedPreferences.edit().putString("name2","wangerniu").commit();
        findViewById(R.id.bt_shujujiegou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShujujiegouActivity.class);
            }
        });
        findViewById(R.id.bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(OptimizationStartActivity.class);
            }
        });
        findViewById(R.id.bt_ui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(UiActviity.class);
            }
        });
    }
}