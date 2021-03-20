package com.yonyou.ceshi.activitymode;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;
import com.yonyou.tool.Util;

public class StandardAActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPrintLifeCycle(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_a);
        Util.printlnActvityStack(this);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandardAActivity.this,StandardBActivity.class);
                startActivity(intent);
                //showDialog();
            }
        });

    }


    private void showDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.mipmap.ic_launcher_round);
        dialog.setTitle("普通 AlertDialog");
        dialog.setMessage("Dialog对话框之：\n AlertDialog");
        dialog.setCancelable(false);    //设置是否可以通过点击对话框外区域或者返回按键关闭对话框
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StandardAActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StandardAActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isFinishing()){
            Log.e("tag","正在关闭-- stop");
        }
    }
}