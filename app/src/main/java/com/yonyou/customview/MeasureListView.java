package com.yonyou.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.yonyou.tool.Util;

public class MeasureListView extends ListView {
    public MeasureListView(Context context) {
        super(context);
    }

    public MeasureListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MeasureListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, // 设计一个较大的值和AT_MOST模式
//                MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);//再调用原方法测量
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Util.print(this.getClass(),"",ev);
        return super.onTouchEvent(ev);
    }
}