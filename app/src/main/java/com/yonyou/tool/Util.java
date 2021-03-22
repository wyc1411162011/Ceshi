package com.yonyou.tool;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

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
        //常规使用
        HashMap<Object,String> hashMap = new HashMap<>();
        Object key1 = new Object();
        Object key2 = new Object();
        Object key3 = new Object();
        Object key4 = new Object();
        Object key5 = new Object();
        Object key6 = new Object();
        hashMap.put(key1,"1");
        hashMap.put(key2,"2");
        hashMap.put(key3,"3");
        hashMap.put(key4,"4");
        hashMap.put(key5,"5");
        hashMap.put(key6,"6");
        printMap(hashMap);
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------"+hashMap.size());
        printMap(hashMap);
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
                Result += line;

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

}
