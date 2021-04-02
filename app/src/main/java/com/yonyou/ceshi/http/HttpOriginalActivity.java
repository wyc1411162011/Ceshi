package com.yonyou.ceshi.http;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpOriginalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_original);
        findViewById(R.id.bt_client_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        userHttpClientGet("http://www.baidu.com");
                    }
                }.start();
            }
        });
        findViewById(R.id.bt_connection_request).setOnClickListener(view -> {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        useHttpConnection("http://www.baidu.com");
                    }
                }.start();
        });
    }
    private HttpClient createHttpClient(){
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams,15000);
        HttpConnectionParams.setSoTimeout(httpParams,15000);
        HttpConnectionParams.setTcpNoDelay(httpParams,true);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        return httpClient;
    }
    private void userHttpClientGet(String url){
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Connection","Keep-Alive");
        try{
            HttpClient httpClient = createHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            //get all headers
            Header[] headers = httpResponse.getAllHeaders();
            for (Header header : headers) {
                Log.e("tag","Key : " + header.getName()
                        + " 的值 :  " + header.getValue());
                System.out.println("Key : " + header.getName()
                        + " 的值 " + header.getValue());
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            int code = httpResponse.getStatusLine().getStatusCode();
            if(httpEntity != null){
                InputStream is = httpEntity.getContent();
                String result = converStreamToString(is);
                Log.e("tag","请求状态码"+code+"    "+result);
                is.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private String converStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line= null;
        while(true){
            try {
                if (!((line = reader.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line+"\n");
        }
        String responce = sb.toString();
        return responce;
    }
    private void useHttpConnection(String url){
        try {

            URL obj = new URL(url);
            URLConnection conn = obj.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestProperty("Connection","Keep-Alive");
            Map<String, List<String>> map = conn.getHeaderFields();

            System.out.println("Printing Response Header...\n");

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " 的值 :   " + entry.getValue());
                Log.e("tag","Key : " + entry.getKey()
                        + " 的值 " + entry.getValue());
            }
            String reuslut = converStreamToString(conn.getInputStream());
            Log.e("tag'","结果"+reuslut);

            System.out.println("\nGet Response Header By Key ...\n");
            String server = conn.getHeaderField("Server");

            if (server == null) {
                System.out.println("Key 'Server' is not found!");
            } else {
                System.out.println("Server - " + server);
            }

            System.out.println("\n Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}