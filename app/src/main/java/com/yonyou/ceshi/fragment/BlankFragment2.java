package com.yonyou.ceshi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yonyou.ceshi.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2021/3/22
 * describle:
 */
public class BlankFragment2 extends BaseFragment{
    @Override
    public void onAttach(@NonNull Context context) {
        setPrintLifeCycle(true);
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.fragment_blank2,container,false);
    }
}
