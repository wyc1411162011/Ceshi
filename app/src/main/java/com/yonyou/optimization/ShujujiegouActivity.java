package com.yonyou.optimization;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

import java.util.HashMap;
//https://segmentfault.com/a/1190000017920239
public class ShujujiegouActivity extends BaseActivity {
    private HashMap<Integer,byte[]>map= new HashMap<>();
    private SparseArray<byte[]>sparseArray = new SparseArray<>();
    private  int number=100000;
    //内存空间SparseArray使用的少 速度也是 SparseArray快很多
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shujujiegou);
        findViewById(R.id.bt_use_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始8.3 结束 14.8 占用6.5M内存空间
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        for(int i=0;i<number;i++){
                            map.put(i,new byte[10]);
                        }
                    }
                }.start();
            }
        });
        findViewById(R.id.bt_use_sparsearray).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始15 结束 19 占用4M内存空间
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        for(int i=0;i<number;i++){
                            sparseArray.put(i,new byte[10]);
                        }
                    }
                }.start();
            }
        });
        findViewById(R.id.bt_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(){
//                    @Override
//                    public void run() {
//                        super.run();
//
//
//                    }
//                }.start();
//                map读取获取的时间171
//                 sparseArray读取获取的时间7

                long currentTime = System.currentTimeMillis();
                for(int i=0;i<number;i++){
                    map.get(i);
                }
                Log.e("tag","map读取获取的时间"+(System.currentTimeMillis()-currentTime));
                currentTime = System.currentTimeMillis();
                for(int i=0;i<number;i++){
                    sparseArray.get(i);
                }
                Log.e("tag","sparseArray读取获取的时间"+(System.currentTimeMillis()-currentTime));
            }
        });
        SparseArray <Integer>integerSparseArray = new SparseArray<>();
        integerSparseArray.put(3,3);
        integerSparseArray.put(1,1);

        integerSparseArray.put(7,7);
        integerSparseArray.put(9,9);
        findViewById(R.id.bt_insert_sparese).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integerSparseArray.put(5,5);
            }
        });
        findViewById(R.id.bt_read_sparese).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<integerSparseArray.size();i++){
                    int key = integerSparseArray.keyAt(i);
                    int value = integerSparseArray.get(key);
                    Log.e("tag","获取的值"+value);
                }
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(~12);
    }
}