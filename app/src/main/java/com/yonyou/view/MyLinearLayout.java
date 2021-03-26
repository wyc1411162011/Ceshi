package com.yonyou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.yonyou.tool.Util;

import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2020-10-14
 * describle:
 */
public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
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
    //外部拦截器
//    private int mLastX,mLastY;
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        Util.print(getClass(),"",event);
//        // 外部拦截法
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                mLastX = (int) event.getX();
//                mLastY = (int) event.getY();
//                break;
//            }
//            case MotionEvent.ACTION_MOVE: {
//                int deltaX = x - mLastX;
//                int deltaY = y - mLastY;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    Util.print(getClass(),"----------------",event);
//                    return true;
//                }
//                break;
//            }
//            case MotionEvent.ACTION_UP: {
//                break;
//            }
//            default:
//                break;
//        }
//
//        return super.onInterceptTouchEvent(event);
//    }


    //内部拦截法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            //交给子view处理 ，只有这设置成false，才会走到子View的dispatch
            Util.print(getClass(),"拦截 "+false,ev);
            return false;
        }else{
            //一直自己处理，但是因为action_down的时候已经交给子View 处理了，子View 设置的是	disallowIntercept为true
            // 父布局的标识这个时候不允许拦截
            //这个时候根本不会走disallowIntercept 为false的时候，这个时候才会拦截
            Util.print(getClass(),"拦截 "+true,ev);
            return true;
        }
    }
}
