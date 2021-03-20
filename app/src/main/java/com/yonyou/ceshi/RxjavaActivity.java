package com.yonyou.ceshi;

import androidx.appcompat.app.AppCompatActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RxjavaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        bindListeners();
    }
    private void bindListeners(){
        findViewById(R.id.bt_create_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOne();
            }
        });
        findViewById(R.id.bt_create_just).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createJust();
            }
        });
    }
    public void createOne(){
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("吴");
                subscriber.onNext("晓畅");
                subscriber.onCompleted();
                Log.e("tag","call所在的线程"+Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Subscriber<String>subscriber= new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("tag","Subscriber "+"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag","Subscriber   "+"onError");
            }

            @Override
            public void onNext(String s) {
                Log.e("tag","Subscriber   "+"onNext  "+ s +"  当前线程"+Thread.currentThread().getName());
            }
        };
        observable.subscribe(subscriber);
    }
    public void createJust(){
        Observable<String> observable = Observable.just("hello","无");
        Subscriber<String>subscriber= new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("tag","Subscriber "+"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag","Subscriber   "+"onError");
            }

            @Override
            public void onNext(String s) {
                Log.e("tag","Subscriber   "+"onNext  "+ s);
            }
        };
        observable.subscribe(subscriber);
    }
}