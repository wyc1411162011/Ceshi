package com.wyc.shejimoshi;

public class LeaveRequest {
    /**
     * 请假的天数
     */
    private int leaveDays;

    /**
     * 请假人的名字
     */
    private String name;

    public LeaveRequest leaveDays(int days){
       this.leaveDays=days;
        return  this;
    }
    public LeaveRequest name(String name){
        this.name=name;
        return this;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public String getName() {
        return name;
    }
}