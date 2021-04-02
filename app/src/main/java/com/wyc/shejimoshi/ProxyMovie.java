package com.wyc.shejimoshi;

/**
 * Created by ufsoft on2021/2/16
 * describle:
 */
public class ProxyMovie implements ImplMovie{
    ImplMovie movie;
    public ProxyMovie(ImplMovie movie){
        this.movie = movie;
    }
    @Override
    public void paly() {
        playGuanggao(true);
        this.movie.paly();
        playGuanggao(false);
    }
    public void playGuanggao(boolean isStart){
        if(isStart){
            System.out.println("正在播放广告");
        }else{
            System.out.println("正在结束播放广告");
        }
    }
}
