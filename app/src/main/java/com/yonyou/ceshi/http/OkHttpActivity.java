package com.yonyou.ceshi.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.yonyou.adapter.BaseListAdapter;
import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

import java.io.File;
import java.io.IOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends BaseActivity {
    private Button bt_tongbu;
    private Button bt_yibu;
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        bindViews();
        bindListeners();
    }

    private void bindViews() {
        bt_tongbu = (Button) findViewById(R.id.bt_tongbu);
        bt_yibu = (Button) findViewById(R.id.bt_yibu);
        lv = (ListView) findViewById(R.id.lv);
    }

    private void bindListeners() {
        bt_tongbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getDataTongbu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        bt_yibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataYibu();
            }
        });
    }

    public void getDataTongbu() throws IOException {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("tag","这个新建的线程名"+Thread.currentThread().getName());
//                try {
//                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
//                    Request request = new Request.Builder()
//                            .url("https://www.baidu.com/")//请求接口。如果需要传参拼接到接口后面。
//                            .build();//创建Request 对象
//                    Response response = null;
//                    response = client.newCall(request).execute();//得到Response 对象
//                    if (response.isSuccessful()) {
////                        Log.e("tag","response.code()=="+response.code());
////                        Log.e("tag","response.message()=="+response.message());
////                        Log.e("tag","res=="+response.body().string());
//                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
//                        Log.e("tag","同步的时候当前的线程名"+Thread.currentThread().getName());
//                    }
//                    Log.e("tag","这个得运行完之后才能执行");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        Object o;
        String string;
        String name = "";
        name.equals("1");

        try {
            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
            Request request = new Request.Builder()
                    .url("https://www.baidu.com/")//请求接口。如果需要传参拼接到接口后面。
                    .build();//创建Request 对象
            Response response = null;
            Call call = client.newCall(request);
            //excute就是同步方法
            response = call.execute();//得到Response 对象
            if (response.isSuccessful()) {
                //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                Log.e("tag", "同步的时候当前的线程名" + Thread.currentThread().getName());
            }
            Log.e("tag", "这个得运行完之后才能执行");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    List<RequestReuslt> list;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            lv.setAdapter(new MyAdapter(context, list));
        }
    };

    class RequestReuslt {
        String ctime;
        String time;
        String qzId;
    }

    private synchronized void getDataYibu() {

        OkHttpClient client = new OkHttpClient();
        //构造一个请求
        final Request request = new Request.Builder()
                .url("https://www.baidu.com/").addHeader("Content-Type", "application/json")
                .build();
        //enqueue异步的方法 通过请求创建一个Call创建一个任务
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //已在主线程中，更新UI
                        }
                    });
                    Log.e("tag", "异步的时候" + Thread.currentThread().getName());
                    Log.e("tag", "获取数据成功了");
                    Log.e("tag", "response.code()==" + response.code());
                    String resultStr = response.body().string();
                    Log.e("tag", "response.body().string()==" + resultStr);

                    handler.sendEmptyMessage(0);
                }
            }


        });
        call.cancel();
    }

    //上传post键值对这种
    private void requestPostKey() {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("username", "zhangsan");//传递键值对参数
        Request request = new Request.Builder()//创建Request 对象。
                .url("http://www.baidu.com")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });//此处省略回调方法。
    }

    private void requestPostJson() {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";//json数据.
        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });//此处省略回调方法。
    }

    private void requestPostFile() {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType fileType = MediaType.parse("File/*");//数据类型为json格式，
        File file = new File("path");//file对象.
        RequestBody body = RequestBody.create(fileType, file);
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });//此处省略回调方法。
    }


    public class MyAdapter extends BaseListAdapter<RequestReuslt> {

        public MyAdapter(Context context, List<RequestReuslt> list) {
            super(context, list);
        }

        @Override
        public View getItemView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_test, null);
                viewHolder = new ViewHolder();
                viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_test);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String text = "";

            RequestReuslt requestReuslt = list.get(position);
            text = text + requestReuslt.qzId + "   ";
            if (!TextUtils.isEmpty(requestReuslt.time)) {
                text = text + "上传的---:" + timestampToDate(Long.parseLong(requestReuslt.time));
            } else {
                text = text + "" + timestampToDate(Long.parseLong(requestReuslt.ctime));
            }
            viewHolder.tv.setText(text);
            final ViewHolder finalViewHolder = viewHolder;
            return convertView;
        }

        class ViewHolder {
            TextView tv;
        }
    }

    public static String timestampToDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        Date date = new Date(time);
        String sd = sdf.format(date);
        return sd;
    }

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i + "");
        }
        System.out.println(arrayList);
        arrayList.addAll(arrayList);

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println("链表的值" + linkedList.get(i));
        }
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(6 >> 1);
        Executors.newCachedThreadPool();
    }

缓存拦截器
    判断是不是可以使用缓存，可以使用缓存返回

    首先缓存必须存在，缓存满足一定的规则，必须在有效期内，不在有效期内，使用对比缓存
    返回304之后，更新有效期


}
