package com.yonyou.ceshi.retrofit;

import retrofit2.Retrofit;

/**
 * Created by ufsoft on2021/4/6
 * describle:
 */
public class RetrifitClient {
    private RetrifitClient(){
    }
    public static RetrifitClient instance = new RetrifitClient();
    private Retrofit createRetrofit(String baseUlr){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUlr).build();
        return retrofit;
    }
    public <T> T getService(String baseUrl,Class<T>service){
        return createRetrofit(baseUrl).create(service);
    }
}
