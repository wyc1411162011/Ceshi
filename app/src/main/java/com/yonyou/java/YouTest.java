package com.yonyou.java;

/**
 * Created by ufsoft on2021/2/5
 * describle:
 */
interface YouTest {
    public JFrame win(String title);
    default int add(int a,int b){
        return a+b;
    }
}
