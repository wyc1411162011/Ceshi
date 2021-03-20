package com.yonyou.proxy;

public class MaotaiJiu implements SellWine {

    @Override
    public void mainJiu() {
        // TODO Auto-generated method stub
        System.out.println("我卖得是茅台酒。");

    }

    @Override
    public void start() {
        System.out.println("茅台酒开始了。。。");
    }

}