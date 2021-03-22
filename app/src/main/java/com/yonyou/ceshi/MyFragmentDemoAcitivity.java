package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yonyou.ceshi.fragment.DynamicFragementActivity;
import com.yonyou.ceshi.fragment.MyFragementShowAndHideActivity;
import com.yonyou.ceshi.fragment.MyStaticFragementActivity;

public class MyFragmentDemoAcitivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment_demo_acitivity);
        findViewById(R.id.bt_static_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyStaticFragementActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_dynamic_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DynamicFragementActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_fragement_show_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyFragementShowAndHideActivity.class);
                startActivity(intent);
            }
        });

    }
}