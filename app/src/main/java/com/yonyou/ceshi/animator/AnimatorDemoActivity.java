package com.yonyou.ceshi.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

public class AnimatorDemoActivity extends BaseActivity {
    private View view;
    private Button bt_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_demo);
        view = findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击事件",Toast.LENGTH_SHORT).show();
            }
        });
        bt_start = (Button)findViewById(R.id.bt_start);

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator=  ObjectAnimator.ofFloat(view,"translationY",-view.getHeight());
                objectAnimator.start();
            }
        });
    }
}