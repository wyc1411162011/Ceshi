package com.yonyou.ceshi;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;


public class WebViewActivity extends Activity {
    WebView wv;
    String url;
    private long startTime,endTime;
    private LinearLayout ll_parent;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");


        ll_parent=(LinearLayout)findViewById(R.id.ll_parent);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        wv=findViewById(R.id.wv);

         url = "file:///android_asset/JsapiDemo.html";
         url = "https://m.baidu.com/?from=1000953f";
        WebSettings setting = wv.getSettings();


        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String contentType = new URL(url).openConnection().getContentType();
                    Log.e("tag","当前的contentType"+ contentType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        setting.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        setting.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
        setting.setSupportZoom(true);//是否可以缩放，默认true
        setting.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        setting.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        setting.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        setting.setAppCacheEnabled(true);//是否使用缓存
        setting.setDomStorageEnabled(true);//开启本地DOM存储
        setting.setLoadsImagesAutomatically(true); // 加载图片
        setting.setMediaPlaybackRequiresUserGesture(false);//播放音频，多媒体需要用户手动？设置为false为可自动播放

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            wv.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        String resultStr = getFromAssets();
       // wv.loadDataWithBaseURL(null, resultStr, "me_userinfo_work_status/html", "utf-8", null);
        wv.loadUrl(url);

        wv.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("tag","获取的title  "+title);
            }
        });

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //调用js方法必须页面显示完之后在调用
                //view.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                endTime= System.currentTimeMillis()/1000;
                Log.e("tag","耗费的时间"+(endTime-startTime));
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                startTime= System.currentTimeMillis()/1000;
                Log.e("tag","开始的的时间"+startTime);
            }
        });

        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//              super.onReceivedSslError(view, handler, error);
                handler.proceed();
//              handleMessage(Message msg); 其他处理
            }
        });

    }

    public String getFromAssets(){
        String fileName = "JsapiDemo.html";
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    };
    private String getAppName(Context context, int pid)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext())
        {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try
            {
                if (info.pid == pid)
                {
                    // 根据进程的信息获取当前进程的名字
                    return info.processName;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }



    @Override
    protected void onDestroy() {
       super.onDestroy();

    }

    @Override
    public void finish() {
        super.finish();
        Toast.makeText(this,name,2000).show();
        Log.e("tag","结束时候的当前的名字"+name);
    }



}
