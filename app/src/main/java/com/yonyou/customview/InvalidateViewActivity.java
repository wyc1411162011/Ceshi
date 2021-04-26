package com.yonyou.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.View;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

public class InvalidateViewActivity extends BaseActivity {
    private CircleView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalidate_view);
        cv = (CircleView)findViewById(R.id.cv);
        findViewById(R.id.bt_main_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.invalidate();
            }
        });
        findViewById(R.id.bt_other_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread("异步任务"){
                    @Override
                    public void run() {
                        super.run();
                        cv.invalidate();
                    }
                }.start();

            }
        });
        findViewById(R.id.bt_main_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.postInvalidate();
            }
        });
        findViewById(R.id.bt_other_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread("异步任务"){
                    @Override
                    public void run() {
                        super.run();
                        cv.postInvalidate();
                    }
                }.start();
            }
        });
    }
}