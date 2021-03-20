package com.yonyou.ceshi;

import android.os.Bundle;

import com.yonyou.ceshi.databinding.ActivityMvvmBinding;
import com.yonyou.viewmodel.MVVMViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MVVMActivity extends AppCompatActivity {
    private ActivityMvvmBinding binding;
    private MVVMViewModel mvvmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm);

        mvvmViewModel = new MVVMViewModel(getApplication(),binding);
        binding.setViewModel(mvvmViewModel);  //初始化viewModel
    }
}