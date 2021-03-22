package com.yonyou.ceshi.fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

public class MyStaticFragementActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPrintLifeCycle(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_static_fragement);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            String simpleName = this.getClass().getSimpleName();
            Log.e("tag",simpleName+"  "+"onActivityResult.....");
        }
    }
}