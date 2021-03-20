package com.yonyou.ceshi;

import android.util.Log;

/**
 * Created by ufsoft on2020-12-31
 * describle:
 */
public class SigleInstance {
    private  static SigleInstance instance;
    private static int number=0;
    private SigleInstance(){

    }
    public static synchronized SigleInstance getInstance(){
        if(instance == null){
            instance = new SigleInstance();
            number ++;
            Log.e("tag","单例重建"+number);
        }
        return instance;
    }
    public static void clearAll(){
        instance = null;
    }
}
