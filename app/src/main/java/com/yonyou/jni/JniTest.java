package com.yonyou.jni;

/**
 * Created by ufsoft on2021/4/15
 * describle:
 */
public class JniTest {
    static {
        System.loadLibrary("jnitest");  // 加载。名字和。CMakeLists.txt  的名字一样
    }
    public native String stringFromJNI();
    public native void test(String name);
    private String instanceField = "java的值instance Field";
    private static String staticField="java的值static Field";

    private String instanceMethod(){
        return "成员方法 instance Method";
    }
    private static String staticMethod(){
        return "静态方法 staticMethod";
    }
    public native void sayHello(long handle);
    public  void sayHello1(long handle){

    }
}
