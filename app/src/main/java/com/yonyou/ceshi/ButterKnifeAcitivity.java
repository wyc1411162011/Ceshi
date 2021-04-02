package com.yonyou.ceshi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ButterKnifeAcitivity extends BaseActivity {
    @BindView(R.id.bt_next)
    Button bt_next;
    @OnClick(R.id.bt_next)
    public void onViewClickedhh(View view){
        Log.e("tag","就开发商的");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_acitivity);
        ButterKnife.bind(this);
//        bt_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("tag","next.....");
//            }
//        });
    }
}