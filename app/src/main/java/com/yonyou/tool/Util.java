package com.yonyou.tool;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * Created by ufsoft on2020-09-10
 * describle:
 */
public class Util {
    public static String qianzhui ="wyc旧";
    public static String GET_URL = qianzhui+"/baidu.com";
    public static void print(Class currentClass,String content){
        String method = Thread.currentThread() .getStackTrace()[3].getMethodName();
        Log.e("tag",content+currentClass.getSimpleName()+" "+ method +"  ");
    }
    public static void print(Class currentClass, String content, MotionEvent ev){
        String method = Thread.currentThread() .getStackTrace()[3].getMethodName();
        String houzhui = "";
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            houzhui = "down";
        }else if(ev.getAction() == MotionEvent.ACTION_MOVE){
            houzhui = "move";
        }else if(ev.getAction() == MotionEvent.ACTION_CANCEL){
            houzhui ="cancel";
        } else {
            houzhui ="up";
        }
        Log.e("tag",content+currentClass.getSimpleName()+" "+ method +"  "+houzhui + "  "+ev.getAction());
    }
    public static String getProcessName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = activityManager.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        int pid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
    public static void printAA(Class currentClass,String content){
        String method = Thread.currentThread() .getStackTrace()[3].getMethodName();
        Log.e("tag",currentClass.getSimpleName()+" "+ method +"  "+ content);
    }
    private static void printMap(Map map){
        for (Object key : map.keySet()) {
            System.out.println("key: " + key + " value: " + map.get(key));
        }
    }
    public static void main(String[] args) {
    }

    public static String getAssertContent(Context context, String fileName)
    {
        try
        {
            InputStreamReader inputReader = new
                    InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line+"、";

            System.out.print(Result);
            return Result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    public static void printlnActvityStack(Activity context){
// 获取activity任务栈
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);

        // 类名 .ui.mobile.activity.WebsiteLoginActivity
        String shortClassName = info.topActivity.getShortClassName();

        // 完整类名 com.haofang.testapp.ui.mobile.activity.WebsiteLoginActivity
        String className = info.topActivity.getClassName();

        // 包名  com.haofang.testapp
        String packageName = info.topActivity.getPackageName();
        Log.e("tag","名字"+context.getClass().getSimpleName()+"   "+context.getTaskId()+" "+ info.description+" "+info);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 将px值转换为sp值
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static void printMeasureModeDes(String qianzhui,int measureSpec){
        String result="";
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int spaceSize = View.MeasureSpec.getSize(measureSpec);
        String des="";
        if(specMode == View.MeasureSpec.UNSPECIFIED){
            des = "UNSPECIFIED";
        }else if(specMode == View.MeasureSpec.AT_MOST){
            des = "AT_MOST";
        }else if(specMode == View.MeasureSpec.EXACTLY){
            des = "EXACTLY";
        }
        Log.e("tag",qianzhui +" 的size"+spaceSize+" 模式"+des);
    }
}
