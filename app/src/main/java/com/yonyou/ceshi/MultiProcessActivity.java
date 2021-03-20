package com.yonyou.ceshi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yonyou.entity.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class MultiProcessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_process);
        bindListeners();
        Student student = new Student();
        student.name="wyc";
        student.age=30;
//        try{
//            String path = getApplicationContext().getExternalFilesDir("").getAbsolutePath()+"/cash.txt";
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
//            out.writeObject(student);
//            out.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
    private void bindListeners(){

        findViewById(R.id.bt_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String path = getApplicationContext().getExternalFilesDir("").getAbsolutePath()+"/cash.txt";
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
                    Student student = (Student) in.readObject();
                    in.close();
                    Toast.makeText(context,"获取的结果"+student.name+"  "+student.age,2000).show();
                    Log.e("tag","获取的结果"+student.name+"  "+student.age);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
