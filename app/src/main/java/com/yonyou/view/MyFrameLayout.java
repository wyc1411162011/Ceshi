package com.yonyou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Random;

import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2020-10-14
 * describle:
 */
public class MyFrameLayout extends LinearLayout {

    public MyFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("tag",this.getClass().getSimpleName()+" --"+"onLayout");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("tag","高度旧 " +oldh +" 新"+h);

        ViewGroup.LayoutParams params=this.getLayoutParams();
        params.height=h;
        this.requestLayout();
    }
}
