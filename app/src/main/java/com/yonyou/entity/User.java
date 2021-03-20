package com.yonyou.entity;

import com.yonyou.ceshi.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by ufsoft on2021/2/16
 * describle:
 */
public class User extends BaseObservable{
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyChange();
    }
    private String name;
    private String password;
}
