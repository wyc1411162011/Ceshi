package com.yonyou.ceshi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

public class ViewDemoActivity extends BaseActivity {
    private Button bt;
    private View view;
    private Button bt_translate;
    private  Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_demo);
        view = findViewById(R.id.view);
        bt =(Button)findViewById(R.id.bt);
        bt1 =(Button)findViewById(R.id.bt1);
        bt2 =(Button)findViewById(R.id.bt2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                params.leftMargin =200;
//                view.setLayoutParams(params);
////                 ObjectAnimator.ofFloat(view,"translationX", 100).start();

            bt.setTranslationZ(100);
                Log.e("tag","bt1,  bt2 "+bt1.getZ() +"  "+bt2.getZ());
            }
        });
        bt_translate = (Button)findViewById(R.id.bt_translate);
        bt_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag","之后的 left right  "+view.getLeft() +" " +view.getTop()+"  "+view.getElevation());
                Log.e("tag","之后的 x,y,z   "+view.getX()+" " +view.getY()+"  "+view.getZ());
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Log.e("tag","初始值 left right  "+view.getLeft() +" " +view.getTop()+"  "+view.getElevation());
            Log.e("tag","初始值 x,y,z  "+view.getX()+" " +view.getY()+"  "+view.getZ());
        }
    }
}