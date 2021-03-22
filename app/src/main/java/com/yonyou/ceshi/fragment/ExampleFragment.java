package com.yonyou.ceshi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yonyou.ceshi.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public  class ExampleFragment extends BaseFragment {
    @Override
    public void onAttach(@NonNull Context context) {
        setPrintLifeCycle(true);
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_dynamic,container,false);
        }
    }