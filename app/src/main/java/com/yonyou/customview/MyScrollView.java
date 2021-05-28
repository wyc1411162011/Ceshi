package com.yonyou.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.yonyou.tool.Util;

/**
 * Created by ufsoft on2021/5/25
 * describle:
 */
public  class MyScrollView extends ScrollView {

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Util.print(this.getClass(),"--------------",ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Util.print(this.getClass(),"--------------",ev);
        return super.onTouchEvent(ev);
    }
}
