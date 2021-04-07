package com.yonyou.ceshi.retrofit;

import android.os.Bundle;
import android.os.FileObserver;
import android.util.Log;
import android.view.View;


import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        findViewById(R.id.bt_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });
        findViewById(R.id.bt_http).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build();
                //代理实例
                WanAndroidApi wanAndroidApi=retrofit.create(WanAndroidApi.class);
                Call<ResponseBody> call=wanAndroidApi.example();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.e("tag","success "+ response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("tag","onFailure...... ");
                    }
                });
            }
        });


    }
    public void getData(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://www.wanandroid.com/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        //1动态代理生成代理接口代理实例
        WanAndroidApi wanAndroidApi=retrofit.create(WanAndroidApi.class);
        //2创建Call
        Call<ProjectBeanResult> call=wanAndroidApi.getProject1();
        //3进行网络请求
        call.enqueue(new Callback<ProjectBeanResult>() {
            @Override
            public void onResponse(Call<ProjectBeanResult> call, Response<ProjectBeanResult> response) {
                Log.e("tag","当前的线程"+Thread.currentThread().getName());
                ProjectBeanResult projectBean = response.body();
                if(projectBean != null){
                    Log.e("tag","success "+projectBean);
                }
            }

            @Override
            public void onFailure(Call<ProjectBeanResult> call, Throwable t) {
                Log.e("tag","onFailure...... ");
            }
        });
    }
    //注解 + 泛型 +动态代理（超级多的设计模式）


    public void uploadFile(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build();
        //代理实例
        WanAndroidApi wanAndroidApi=retrofit.create(WanAndroidApi.class);
        File file = new File("");
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/png"),file);
        Call<ResponseBody> call = null;
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
