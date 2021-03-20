package com.yonyou.tool;

import android.content.Context;

public class DBHelper{
    private static DBHelper mInstance;
    public   static DBHelper getInstance() {
        /**
         * 只有在为空的时候，会有同步锁的影响 
         */
        if (mInstance == null) {
            synchronized (DBHelper.class) {
                if (mInstance == null) {
                    mInstance = new DBHelper();
                }
            }
        }
        return mInstance;
    };
    public synchronized void write(){
        System.out.println(Thread.currentThread().getName()+"  开始写入数据库");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"  结束写入数据库");
    }
}