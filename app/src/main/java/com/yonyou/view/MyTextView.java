package com.yonyou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yonyou.tool.Util;

import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2020-10-14
 * describle:
 */
public class MyTextView extends TextView {

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Util.print(getClass(),"",ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Util.print(getClass(),"",event);
        return super.onTouchEvent(event);
    }

}
