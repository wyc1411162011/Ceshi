package com.yonyou.ceshi;

import android.Manifest;
import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.brtbeacon.sdk.demo.LwfUtil;
import com.google.gson.reflect.TypeToken;
import com.yonyou.ceshi.activitymode.StandardAActivity;
import com.yonyou.service.MyIntentService;
import com.yonyou.service.MyService;
import com.yonyou.tool.GsonUtils;
import com.yonyou.tool.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends BaseActivity {
    private LinearLayout ll_parent ;
    private Button bt_change_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_change_address = (Button) findViewById(R.id.bt_change_address);
        bindListeners();
        ll_parent = (LinearLayout)findViewById(R.id.ll_parent);
        String CACHE_DIR = "DataCache";
        File f = new File(getCacheDir(), CACHE_DIR);

    }
    public static void getAppIsFirstInstall(Context context){
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            if(packageInfo.lastUpdateTime == packageInfo.firstInstallTime){
                Log.e("tag","首次安装.....");


            }else{
                Log.e("tag","不是首次安装.....");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void println(){
        Log.e("tag","打印----");
    }
    private void stop(){
        Log.e("tag","stop----");
    }
    private HandlerThread handlerThread = new HandlerThread("hah");
    private Handler myHandler;
    private void bindListeners(){
        handlerThread.start();
        myHandler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                println();
                stop();
            }
        };
        findViewById(R.id.bt_executors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,MyFragmentDemoAcitivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_annotation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AnnotationActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_multi_process).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MultiProcessActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_get_back_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        findViewById(R.id.bt_kotlin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,KotlinActivity.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.bt_get_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag","地址  "+Util.GET_URL);
            }
        });
        findViewById(R.id.bt_change_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.qianzhui="新的    ";
            }
        });


    }
    public static String toHexEncoding(int color) {
        String R, G, B;
        StringBuffer sb = new StringBuffer();
        R = Integer.toHexString(Color.red(color));
        G = Integer.toHexString(Color.green(color));
        B = Integer.toHexString(Color.blue(color));
        //判断获取到的R,G,B值的长度 如果长度等于1 给R,G,B值的前边添0
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        sb.append("0x");
        sb.append(R);
        sb.append(G);
        sb.append(B);
        return sb.toString();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("tag",getClass().getSimpleName() +"  "+" onDestroy");
    }

    class JavaBean<T>{
        String code;
        T data;
    }

    @Override
    public void finish() {
        super.finish();
        Log.e("tag","finish-------");
    }
}