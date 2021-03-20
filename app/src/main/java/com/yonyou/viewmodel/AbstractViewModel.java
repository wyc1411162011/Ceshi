package com.yonyou.viewmodel;

import java.util.WeakHashMap;

import androidx.databinding.ViewDataBinding;

public abstract class AbstractViewModel<T extends ViewDataBinding> implements BaseViewModel {
    public T mViewDataBinding;
    public AbstractViewModel(T viewDataBinding)
    {
        this.mViewDataBinding=viewDataBinding;
    }

    @Override
    public void onDestroy() {
        mViewDataBinding.unbind();
    }
    static Object key1,key2,key3;
    public static void main(String[] args) {
        WeakHashMap<Object, Integer> weakHashMap = getWeakHashMap();
        printWeakHashMapInfo(weakHashMap);

        // 由于weakHashMap的所有键值都有一个强引用与之关联，所以weakHashMap的所有键值对都不会被回收
//        System.gc();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        printWeakHashMapInfo(weakHashMap);

        // 将其中一个键值对应的强引用置为null，让Java虚拟机来进行自动回收
        key1 = null;
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printWeakHashMapInfo(weakHashMap);
    }

    private static WeakHashMap<Object, Integer> getWeakHashMap() {
        WeakHashMap<Object, Integer> weakHashMap = new WeakHashMap<>();

        key1 = new Object();
        key2 = new Object();
        key3 = new Object();

        weakHashMap.put(key1, 1);
        weakHashMap.put(key2, 2);
        weakHashMap.put(key3, 3);

        return weakHashMap;
    }

    private static void printWeakHashMapInfo(WeakHashMap<Object, Integer> weakHashMap) {
        System.out.println("weakHashMap.size() = " + weakHashMap.size());

        for (Object key : weakHashMap.keySet()) {
            System.out.println("key: " + key + " value: " + weakHashMap.get(key));
        }

        System.out.println("----------------------------------------------");
    }
}