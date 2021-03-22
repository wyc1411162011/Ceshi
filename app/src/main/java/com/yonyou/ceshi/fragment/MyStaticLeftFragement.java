package com.yonyou.ceshi.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yonyou.ceshi.R;
import com.yonyou.ceshi.activitymode.StandardAActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyStaticLeftFragement extends BaseFragment {
    @Override
    public void onAttach(@NonNull Context context) {
        setPrintLifeCycle(true);
        super.onAttach(context);
    }
    private Button bt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_my_static_left_fragement, container, false);
        bt = (Button) view.findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Activity activity = null;
                if(context instanceof Activity){
                    activity =(Activity)context;
                }
                Intent intent = new Intent(context, StandardAActivity.class);
                if(activity != null){
                    activity.startActivityForResult(intent,100);
                }

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            String simpleName = this.getClass().getSimpleName();
            Log.e("tag",simpleName+"  "+"onActivityResult.....");
        }
    }
}