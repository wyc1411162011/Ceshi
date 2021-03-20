package com.yonyou.viewmodel;

import android.app.Application;
import android.view.View;

import com.google.gson.internal.$Gson$Preconditions;
import com.yonyou.callback.MCallback;
import com.yonyou.ceshi.BR;
import com.yonyou.ceshi.databinding.ActivityMvvmBinding;
import com.yonyou.entity.Account;
import com.yonyou.model.MVVMModel;
import com.yonyou.tool.DBHelper;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class MVVMViewModel extends BaseObservable {

    private ActivityMvvmBinding binding;
    private MVVMModel mvvmModel;
    private String Input;
    private String result;

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }
    //    一般需要传入Application对象，方便在ViewModel中使用application
    //    比如sharedpreferences需要使用
    public MVVMViewModel(Application application, ActivityMvvmBinding binding) {
        this.binding=binding;
        mvvmModel = new MVVMModel();

    }

    public void getData(View view){

        Input = binding.etAccount.getText().toString();
        mvvmModel.getAccountData(Input, new MCallback() {
            @Override
            public void onSuccess(Account account) {
                String info = account.getName() + "|" + account.getLevel();
                setResult(info);
            }

            @Override
            public void onFailed() {
                setResult("消息获取失败");
            }
        });
    }
    private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
    private static class ProduceThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                queue.put("produce..");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("produce---------------------------");
        }
    }
    private static class Consumer extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                String product = queue.take();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("sonsume..");
        }
    }
    private int number;
    public void add10000(){
        int number =0;
        for(int i=0;i<100000;i++){
            number++;
        }
        System.out.println(number);
    }
    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private final int threadLocalHashCode = nextHashCode();
    /**
     * The difference between successively generated hash codes - turns
     * implicit sequential thread-local IDs into near-optimally spread
     * multiplicative hash values for power-of-two-sized tables.
     */
    private static final int HASH_INCREMENT = 0x61c88647;

    /**
     * Returns the next hash code.
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("初始化的值"+ref.get());
        Thread thread = Thread.currentThread();
        boolean result =ref.compareAndSet(thread,null);
        System.out.println(result);
        System.out.println("比较之后的值"+ref.get());
    }
    private static AtomicReference<Thread> ref = new AtomicReference<>();

}