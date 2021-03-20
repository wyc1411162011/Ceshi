package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yonyou.event.UpdateEnvent;
import com.yonyou.tool.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Intent intent = getIntent();
        String name =intent.getStringExtra("name");
        EventBus.getDefault().register(this);
        findViewById(R.id.bt_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateEnvent updateEnvent = new UpdateEnvent();
                updateEnvent.name="哈哈";
                EventBus.getDefault().postSticky(updateEnvent);
            }
        });
        findViewById(R.id.bt_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NextActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void get(UpdateEnvent envent) {
        Util.print(this.getClass(),envent.name);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void haode(UpdateEnvent envent) {
        Util.print(this.getClass(),envent.name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
