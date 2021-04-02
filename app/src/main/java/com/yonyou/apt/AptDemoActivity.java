package com.yonyou.apt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

/**
 * 编译注解解析的方法
 *
 */
public class AptDemoActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apt_demo);
    }
}