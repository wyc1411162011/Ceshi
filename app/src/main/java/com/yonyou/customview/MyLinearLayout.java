package com.yonyou.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.yonyou.tool.Util;

import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2021/4/22
 * describle:
 */
public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Util.printMeasureModeDes("宽度",widthMeasureSpec);
        Util.printMeasureModeDes("高度" ,heightMeasureSpec);
        Log.e("tag","onMeasure测量过后的宽"+getMeasuredWidth()+" 高"+getMeasuredHeight());
        Util.print(getClass(),"" +" 线程 "+Thread.currentThread().getName()+" ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Util.print(getClass(),"" +" 线程 "+Thread.currentThread().getName()+" ");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Util.print(getClass(),"" +" 线程 "+Thread.currentThread().getName()+" ");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Util.print(getClass(),"" +" 线程 "+Thread.currentThread().getName()+" ");
    }
}
