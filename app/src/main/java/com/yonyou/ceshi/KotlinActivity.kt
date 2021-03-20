package com.yonyou.ceshi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.JsonObject
import com.yonyou.tool.Util

/**
 *
 *Created by ufsoft on2021/2/2
 *describle:
 */
class KotlinActivity : Activity() {
    var et:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        et =findViewById(R.id.et)
        findViewById<Button>(R.id.bt).setOnClickListener({
            et?.setText("最新设置的属性1234")
        })
        Util.print(javaClass,"调用java的方法"+"  println")
    }
    fun onclick1(view: View){
        et?.setText("设置的属性")
    }
    fun showToust(view:View){
        Toast.makeText(this,et?.text,Toast.LENGTH_LONG).show()
    }
    fun jumpActivity(view:View){
        var intent = Intent(this,KotlinSecondActivity::class.java)
        intent.putExtra("name","wyc")
        intent.putExtra("age",11)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                if(resultCode== Activity.RESULT_OK){
                    var who:String?= data?.getStringExtra("who")
                    if(who !=null){
                        Log.e("tag","who "+who)
                    }
                }

            }
        }
    }

}