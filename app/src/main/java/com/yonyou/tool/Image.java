package com.yonyou.tool;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;

/**
 * Created by ufsoft on2021/2/16
 * describle:
 */
public class Image {
    @BindingAdapter({"url"})
    public static void loadImage(ImageView view, String na) {
        Log.e("tag", "loadImage url : " + na);
    }
    @BindingAdapter("android:text")
    public static void set(Button button, String text) {
       String name=text+"--";
       Log.e("tag","所有的button要设置");
       button.setText(name);
    }
    @BindingConversion
    public static String conte(String text){
        return text +"  转换";
    }

    @BindingConversion
    public static Drawable convertStringToDrawable(String str) {
        if (str.equals("红色")) {
            return new ColorDrawable(Color.parseColor("#FF4081"));
        }
        if (str.equals("蓝色")) {
            return new ColorDrawable(Color.parseColor("#3F51B5"));
        }
        return new ColorDrawable(Color.parseColor("#344567"));
    }
}
