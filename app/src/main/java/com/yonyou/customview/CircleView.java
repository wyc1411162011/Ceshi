package com.yonyou.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yonyou.tool.Util;

import androidx.annotation.Nullable;

/**
 * Created by wyc on2021/4/22
 * describle:
 */
public class CircleView extends View {
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private int color= Color.RED;
    private Paint paint = new Paint();
    private void init(){
        paint.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode ==MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST ) {
            setMeasuredDimension(600,600);
        }else if(widthSpecMode ==MeasureSpec.AT_MOST ){
            setMeasuredDimension(600,heightMeasureSpec);
        }else if(heightMeasureSpec == MeasureSpec.AT_MOST ){
            setMeasuredDimension(widthMeasureSpec,600);
        }
        Util.print(getClass(),""+" 线程名:"+Thread.currentThread().getName()+" ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight= getPaddingRight();
        final int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int radiusWidth= getWidth() -paddingLeft-paddingRight;
        int radiusHeight = getHeight()-paddingTop-paddingBottom;
        int radius = Math.min(radiusWidth,radiusHeight)/2;
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
        Util.print(getClass(),""+" 线程名:"+Thread.currentThread().getName()+" ");
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Util.print(getClass(),""+" 线程名:"+Thread.currentThread().getName()+" ");
    }
}
