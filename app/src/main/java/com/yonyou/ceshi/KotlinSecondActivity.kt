package com.yonyou.ceshi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 *
 *Created by ufsoft on2021/2/15
 *describle:
 */
class KotlinSecondActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_second)
        var intent=intent
        var name:String ? = intent.getStringExtra("name")
        var age:Int? =intent.getIntExtra("age",1)
        if(name !=null){
            Log.e("tag","name"+name+" 年龄"+age)
        }
    }
    fun close(view:View){
        var intent:Intent = Intent()
        intent.putExtra("who","二妞")
        setResult( Activity.RESULT_OK,intent)
        finish()
    }
}