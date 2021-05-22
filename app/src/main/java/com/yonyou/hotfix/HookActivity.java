package com.yonyou.hotfix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

public class HookActivity extends BaseActivity {
    private Button btn_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        btn_test = findViewById(R.id.bt);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Button Click", Toast.LENGTH_SHORT).show();
            }
        });

        HookView.hookOnClickListener(btn_test);
    }
}