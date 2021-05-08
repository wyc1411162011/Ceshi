package com.yonyou.optimization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;
import com.yonyou.tool.Util;

public class OptimizationDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimization_demo);
        Log.e("tag","第2个进程的 "+ Util.number);
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