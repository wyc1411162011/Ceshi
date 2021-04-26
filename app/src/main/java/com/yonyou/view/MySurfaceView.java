package com.yonyou.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.yonyou.tool.Util;

/**
 * Created by wyc on2021/4/25
 * describle:
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsRunning;
    private Paint paint;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsRunning = true;
        new Thread(this).start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsRunning = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();

        while (mIsRunning) {
            Util.print(getClass()," 线程名: "+Thread.currentThread().getName()+" ");
            draw();
        }
    }

    private void draw() {
        mCanvas = mHolder.lockCanvas();
        if (mCanvas != null) {
            try {
                //使用获得的Canvas做具体的绘制
                final int paddingLeft = getPaddingLeft();
                final int paddingRight= getPaddingRight();
                final int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int radiusWidth= getWidth() -paddingLeft-paddingRight;
                int radiusHeight = getHeight()-paddingTop-paddingBottom;
                int radius = Math.min(radiusWidth,radiusHeight)/2;
                mCanvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}