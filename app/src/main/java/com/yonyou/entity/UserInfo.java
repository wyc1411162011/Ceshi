package com.yonyou.entity;

import com.yonyou.ceshi.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public  class UserInfo  extends BaseObservable
    {
        private int age;
        private String name;
 
        @Bindable
        public int getAge() {
            return age;
        }
 
        public void setAge(int age) {
            this.age = age;
            notifyPropertyChanged(BR.age);
        }
 
        @Bindable
        public String getName() {
            return name;
        }
 
        public void setName(String name) {
            this.name = name;
            notifyPropertyChanged(BR.name);
        }
    }