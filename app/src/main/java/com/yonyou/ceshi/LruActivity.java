package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LruActivity extends Activity {
    LruCache<String, Bitmap> lruCache;
    private ImageView iv_lru_cache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru);
        bindViews();
        init();
        bindListeners();
        HashMap<String,String>map = new HashMap<>();
        LinkedHashMap<String,String>linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("","");
        Hashtable<String,String>hashtable = new Hashtable<>();
        hashtable.put("","");
    }
    private void bindViews(){
        iv_lru_cache = (ImageView)findViewById(R.id.iv_lru_cache);
    }
    private void init(){
        lruCache = new LruCache<String,Bitmap>(20*1024*1024){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                Log.e("tag","sizeof");
                return value.getByteCount();
            }
        };
        Resources res = getResources();
        Bitmap bmp= BitmapFactory.decodeResource(res, R.drawable.door_bg2);
        Log.e("tag","size "+bmp.getByteCount());
        lruCache.put("key0",bmp);


    }
    private void bindListeners(){
        findViewById(R.id.bt_lru_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = lruCache.get("key0");
                if(bitmap!=null){
                    iv_lru_cache.setImageBitmap(bitmap);
                }
            }
        });
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String,String>(16,0.75f,true);
        linkedHashMap.put("name1", "josan1");
        linkedHashMap.put("name2", "josan2");
        linkedHashMap.put("name3", "josan3");
        linkedHashMap.get("name2");
        linkedHashMap.get("name3");
        linkedHashMap.get("name1");
        Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
    }
}