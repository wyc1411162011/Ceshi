package com.wyc.shejimoshi;

/**
 * Created by ufsoft on2021/2/16
 * describle:
 */
public class Movie implements ImplMovie{
    @Override
    public void paly() {
        System.out.println("正在播放电影");
    }
}
