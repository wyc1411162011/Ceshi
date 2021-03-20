package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;

import com.yonyou.ceshi.databinding.ActivitySampleMvvmBinding;

public class SampleMvvmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySampleMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_mvvm);
    }
}