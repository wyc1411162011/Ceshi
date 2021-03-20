package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LruCache;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapActivity extends Activity {
    private LinkedHashMap<String,String>linkedHashMap= new LinkedHashMap<>();
    private LinkedHashMap<String,String>visitMap= new LinkedHashMap<>(6,0.75f,true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_hash_map);
        for(int i=0;i<10;i++){
            linkedHashMap.put(i+"",i+"");
        }
        for(int i=0;i<10;i++){
            visitMap.put(i+"",i+"");
        }
        //访问的顺序当访问的时候把这个元素放到双链表的尾部，最后才能访问到
        visitMap.get("7");
        visitMap.get("6");
        findViewById(R.id.bt_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(Map.Entry<String,String> entry:linkedHashMap.entrySet()){
//                    Log.e("tag","key "+entry.getKey()+" "+"value "+entry.getValue());
//                }
                printMap("插入访问的 ：",linkedHashMap);
            }
        });
        findViewById(R.id.bt_visit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(Map.Entry<String,String> entry:visitMap.entrySet()){
//                    Log.e("tag","访问顺序的key "+entry.getKey()+" "+"value "+entry.getValue());
//                }
                printMap("访问顺序的: ",visitMap);
            }
        });
    }
    //访问顺序从双链表的头部开始顺序访问
    private void printMap(String tag,HashMap<String,String> hm){
        Iterator<Map.Entry<String,String>> it = hm.entrySet().iterator();
         while(it.hasNext()){
             Map.Entry<String ,String> entry = it.next();
             String key = entry.getKey();
             String value = entry.getValue();
             Log.e("tag",tag+ "key的值 "+ key+" value的值"+value);
         }

    }
}