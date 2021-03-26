package com.yonyou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yonyou.tool.Util;

import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2020-10-14
 * describle:
 */
public class MyView extends View {

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
//    //外部拦截法默认的不做任何处理
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Util.print(getClass(),"",ev);
//        return super.dispatchTouchEvent(ev);
//    }
    //内部拦截法
    private int mLastX, mLastY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("tag","----------------"+" dispatchTouchEvent");
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;

            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean isTouch=  super.onTouchEvent(event);
        Util.print(getClass()," ",event);
        return  true;
    }

}
