package com.yonyou.ceshi.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yonyou.ceshi.R;

public class DynamicFragementActivity extends FragmentActivity

{
    ExampleFragment fragment;
    FragmentManager fragmentManager;
    MyStaticLeftFragement leftFragement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragement);
        fragmentManager = getSupportFragmentManager();
         fragment = new ExampleFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.ll_container,fragment);
        fragmentTransaction.commit();
        findViewById(R.id.bt_replace_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 leftFragement = new MyStaticLeftFragement();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.ll_container,leftFragement);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

}