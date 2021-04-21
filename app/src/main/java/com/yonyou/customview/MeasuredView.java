package com.yonyou.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yonyou.tool.Util;

import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2021/4/20
 * describle:
 */
public class MeasuredView extends View {
    private Context context;
    public MeasuredView(Context context){
        super(context);
        this.context = context;
    }
    public MeasuredView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    private  int index;
    public void setIndex(int index){
        this.index=index;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(Util.dip2px(context,18));
        paint.setColor(Color.RED);
        canvas.drawText(index+"",getWidth()/2-Util.dip2px(context,18),Util.dip2px(context,18),paint);
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
    }
}
