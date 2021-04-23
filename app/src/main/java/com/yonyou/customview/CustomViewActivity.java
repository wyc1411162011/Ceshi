package com.yonyou.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;
import com.yonyou.tool.Util;

public class CustomViewActivity extends BaseActivity {
    private View v_beyond_parent;
    private MyLinearLayout my_ll;
    private  HorizontalScrollViewEx hsve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        v_beyond_parent = findViewById(R.id.v_beyond_parent);
        my_ll = (MyLinearLayout)findViewById(R.id.my_ll);
        hsve = (HorizontalScrollViewEx)findViewById(R.id.hsve);
        findViewById(R.id.bt_invalidate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InvalidateViewActivity.class);
            }
        });

        v_beyond_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("tag","横向的自定义的控件的宽"+hsve.getWidth()+" 高"+hsve.getHeight());
//                Log.e("tag","宽度"+v_beyond_parent.getWidth()+"  "+"高度"+v_beyond_parent.getHeight()+"  "+Util.dip2px(context,100));
//                Log.e("tag","父布局的宽高"+my_ll.getWidth()+" "+my_ll.getHeight());

              new Thread("哈哈"){
                  @Override
                  public void run() {
                      super.run();
                      my_ll.invalidate();
                  }
              }.start();

            }
        });
        findViewById(R.id.bt_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CustomView1Activity.class);
            }
        });
    }





}