package com.yonyou.ceshi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyc.base.BaseModuleActivity;
import com.wyc.jnidemo.CTest;
import com.yonyou.aidl.AidlDemoActivity;
import com.yonyou.ceshi.dispatch.DispatchDemoActviity;
import com.yonyou.ceshi.retrofit.RetrofitActivity;
import com.yonyou.contentprovider.ContentProviderDemoActivity;
import com.yonyou.customview.CustomViewActivity;
import com.yonyou.customview.ListViewInScrollViewActivity;
import com.yonyou.hotfix.HookActivity;
import com.yonyou.jni.JniTest;
import com.yonyou.optimization.OptimizationDemoActivity;
import com.yonyou.tool.Util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import androidx.annotation.NonNull;

public class MainActivity extends BaseActivity {
    private LinearLayout ll_parent ;
    private Button bt_change_address;
//    static {
//        System.loadLibrary("native-lib");
//    }
    private TextView tv_show_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_change_address = (Button) findViewById(R.id.bt_change_address);
        tv_show_time = (TextView)findViewById(R.id.tv_show_time);
        bindListeners();
        ll_parent = (LinearLayout)findViewById(R.id.ll_parent);
        String CACHE_DIR = "DataCache";
        File f = new File(getCacheDir(), CACHE_DIR);
        Log.e("tag","第一个进程的"+Util.number);

    }
    private void setTimeShow(TextView tv,String time){
        time="2021-04-24 19:57";
        if(!TextUtils.isEmpty(time) && tv != null){
            java.text.DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = null;
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int years = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
           int min = calendar.get(Calendar.MINUTE);
           int week = calendar.get(Calendar.DAY_OF_WEEK);
           //tv.setText(years+"年"+month+"月"+day+"日"+hour+"时"+min+"分"+" 星期"+week);
        }
        ForegroundColorSpan topColorSpan = new ForegroundColorSpan(Color.RED);
        AbsoluteSizeSpan topAbsoluteSize =  new AbsoluteSizeSpan(25, true);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("哈哈");
        builder.setSpan(topColorSpan,0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(topAbsoluteSize,0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("哈哈\n");
        int lastLength= builder.toString().length();
        builder.append("星期日");
        ForegroundColorSpan bottomColorSpan = new ForegroundColorSpan(Color.BLUE);
        AbsoluteSizeSpan bottomAboluteSize = new AbsoluteSizeSpan(sp2px(context,13), true);
        builder.setSpan(bottomColorSpan,lastLength,builder.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(bottomAboluteSize,lastLength,builder.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }
    public  int sp2px(Context context, float spValue) {
        int size= getResources().getDimensionPixelSize(R.dimen.font_13);
        return size;
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
                Looper.prepare();
                Handler handler= null;
                handler.removeCallbacksAndMessages(null);
                stop();
            }
        };
        findViewById(R.id.bt_executors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PackageManager pm = getPackageManager();
                    ApplicationInfo ai = pm.getApplicationInfo("com.gesoft.bit.lavendercloud", PackageManager.GET_ACTIVITIES);
                    Log.d("tag", "!!" + ai.uid);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
//                startActivity(ListViewInScrollViewActivity.class);
                //setTimeShow(tv_show_time,null);

//                SharedPreferences sharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("name","王永超");
//                editor.putString("age","30岁");
//                editor.commit();
                //startActivity(ListViewInScrollViewActivity.class);


//                WindowManager.LayoutParams mWindowAttributes = new WindowManager.LayoutParams();
//                System.out.println(mWindowAttributes.width+"  "+mWindowAttributes.height);
//                Log.e("tag",mWindowAttributes.width+"  "+mWindowAttributes.height);
//                new JniTest().test("android传递");
//                new JniTest().sayHello(1);



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
    public synchronized void  eate(){

    }
    @Override
    public void finish() {
        super.finish();
        Handler handler = new Handler();
        Log.e("tag","finish-------");
    }

    protected void executeBadge(int badgeCount) {
        String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
         String INTENT_EXTRA_BADGE_COUNT = "badge_count";
         String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";
         String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
        Intent intent = new Intent(INTENT_ACTION);
        intent.putExtra(INTENT_EXTRA_BADGE_COUNT, badgeCount);
        intent.putExtra(INTENT_EXTRA_PACKAGENAME, getContextPackageName());
        intent.putExtra(INTENT_EXTRA_ACTIVITY_NAME, getEntryActivityName());
        sendBroadcast(intent);
    }
    protected String getContextPackageName() {
        return getPackageName();
    }
    protected String getEntryActivityName() {
        ComponentName componentName = getPackageManager().getLaunchIntentForPackage(getPackageName()).getComponent();
        return componentName.getClassName();
    }

    public static void main(String[] args) {
        StringBuilder builder= new StringBuilder();
        builder.append(1);
        builder.append("hh");
        System.out.println(builder);
    }
}
