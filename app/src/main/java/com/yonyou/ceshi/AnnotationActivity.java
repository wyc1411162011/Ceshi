package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.yonyou.annotation.ClassAnnotation;
import com.yonyou.annotation.RuntimeAnnotation;
import com.yonyou.annotation.SourceAnnotation;
import com.yonyou.tool.Util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationActivity extends BaseActivity{
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            if(method != null){
                Annotation[] annotationArr = method.getAnnotations();
                if(annotationArr!= null){
                    for(int i=0;i<annotationArr.length;i++){
                        Log.e("tag",method.getName()+" ---"+ annotationArr[i].toString());
                    }

                }
            }
        }
        iv =(ImageView)findViewById(R.id.iv);
        setAWyongchao();
        Util.printAA(getClass()," 这个名字");
    }
    private void setAWyongchao(){
        System.out.println("hah ");
        long currentTime = System.currentTimeMillis();
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setImageResource(R.drawable.door_bg2);
        Log.e("tag",System.currentTimeMillis()-currentTime+"   shijian ");

    }
    public void testRunTimeAnnotation(){

    }
    @ClassAnnotation
    public void testClassAnnotation(){

    }
    @SourceAnnotation(name = @RuntimeAnnotation())
    public void testSourceAnnotation(){

    }

}
