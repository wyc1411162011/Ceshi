package com.yonyou.optimization.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yonyou.ceshi.R;

import androidx.annotation.Nullable;

/**
 * Created by wyc on2021/5/7
 * describle:
 */
public class UiMyLinearLayout extends LinearLayout {
    private Context context;
    public UiMyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init(){
        View view = LayoutInflater.from(context).inflate(R.layout.ui_my_linearlayout, this, true);
        setOrientation(VERTICAL);
    }
}
