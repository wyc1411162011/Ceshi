package com.yonyou.ceshi.dispatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

public class DispatchDemoActviity extends BaseActivity {
    private LinearLayout ll_parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_demo_actviity);
        ll_parent = (LinearLayout)findViewById(R.id.ll_parent);
    }
}