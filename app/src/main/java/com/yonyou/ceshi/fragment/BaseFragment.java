package com.yonyou.ceshi.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yonyou.tool.Util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by wyc on2021/3/22
 * describle:
 */
public class BaseFragment extends Fragment {
    private boolean isPrintLifeCycle =false;
    private String printQianzhui = "fragment_______";
    public void setPrintLifeCycle (boolean isPrintLifeCycle){
        this.isPrintLifeCycle = isPrintLifeCycle;
    }

    @Override
    public void onAttach(@NonNull Context context) {

        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onStart();
    }

    @Override
    public void onResume() {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        if(isPrintLifeCycle){
            Util.print(getClass(),printQianzhui);
        }
        super.onDetach();
    }
}
