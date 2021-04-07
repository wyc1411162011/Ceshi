package com.yonyou.ceshi.retrofit;


import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ufsoft on2021/4/6
 * describle:
 */
 interface WanAndroidApi {
     //path  完整的path 实在 baseUrl+ path（下面的）
    @GET("project/tree/json")
    public Call<ProjectBean> getProject();

    @GET("project/tree/json")
    public Call<ProjectBeanResult> getProject1();
    @HTTP(method = "get",path = "project/tree/json")
    public Call<ResponseBody> example();//默认返回的是ResponseBody ,返回实体类的上面的是由GsonConverterFactory转的ResponseBody形成的
    @Streaming
    @GET
    public Call<ResponseBody> downloadFile(@Url String url);
    @POST("path/cesh")
    public Call<ResponseBody>example2(@Body ResponseBody ResponseBody);
    @POST("path/cesh")
    //单个   多个数组    兼职对
    public Call<ResponseBody>example3(@Field("name") String name, @Field("array") ArrayList<String> arr
    , @FieldMap Map<String,String>map);
    @GET("project/{id}/list")
    public Call<ResponseBody>example5(@Path("id") int id);
    @GET("xx")
    public Call<ResponseBody> search(@Query("name") String name
    , @QueryMap Map<String,String>map);
    //上传问题
    @Multipart
    @POST("project/upload")
    public Call<ResponseBody> uploadFile1(@Part("file\";filename=\"test.png")RequestBody file);
   @Multipart
   @POST("project/upload")
   public Call<ResponseBody> uploadFile2(@Part Multipart file);

 }
