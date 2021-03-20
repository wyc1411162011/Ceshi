package com.yonyou.ceshi.fragment;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yonyou.ceshi.R;

import androidx.fragment.app.Fragment;

public class MyStaticLeftFragement extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_static_left_fragement, container, false);
    }
}