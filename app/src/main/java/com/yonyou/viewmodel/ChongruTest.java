package com.yonyou.viewmodel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChongruTest extends Thread {
   
    Lock lock =new ReentrantLock();
    
      @Override
    public void run() {
        set(); 
        
    }
    
   public  void  set(){
       try {
           lock.lock();
           System.out.println("set方法");
           get();
    } catch (Exception e) {
           e.printStackTrace();
    }finally {
        lock.unlock();
    }
      
   }
   public  void get(){
       System.out.println("lock 可以具备可重入性-get方法");
       try {
        lock.lock();
    } catch (Exception e) {
           e.printStackTrace();
    }finally {
        lock.unlock();
    }
   }
      
    public static void main(String[] args) {
        ChongruTest chongruTest =  new ChongruTest();
        chongruTest.start();
    }
}
