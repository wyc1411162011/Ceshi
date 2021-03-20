package com.yonyou.ceshi;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yonyou.ceshi.databinding.ActivityDataBindingShowBinding;
import com.yonyou.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataBindingActivity extends Activity {
    private User userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_show);
        ActivityDataBindingShowBinding activityBinding= DataBindingUtil.setContentView(this,R.layout.activity_data_binding_show);
        userInfo = new User("王二妞","1289");
        activityBinding.setUserInfo(userInfo);
        activityBinding.setUserHandler(new UserHandler());
        ObservableList<String> list=new ObservableArrayList<>();
        list.add("list的0index");
        activityBinding.setList(list);
        ObservableMap<String,String>map = new ObservableArrayMap<>();
        map.put("key0","key0的值");
        activityBinding.setMap(map);
        userInfo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(propertyId == BR.name){
                    Log.e("tag","修改name的值");
                }
                Log.e("tag","修改某一个值");
            }
        });

    }
    public class UserHandler{
        public void changeName(){
            userInfo.setName("单个修改之后的Name");
            userInfo.setPassword("单个修改之后的password");
        }
        public void changePassword(){
          //  userInfo.setName("全部修改之后的Name");
            userInfo.setPassword("全部修改之后的password");
        }

    }
}