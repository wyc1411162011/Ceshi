package com.yonyou.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.View;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class AidlDemoActivity extends BaseActivity {
    IBookManager iBookManager;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
             iBookManager = IBookManager.Stub.asInterface(service);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayBlockingQueue a;
        setContentView(R.layout.activity_aidl_demo);
        Intent intent = new Intent(this,BookManageService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
        findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iBookManager !=null){
                    try {
                        List<Book> bookList = iBookManager.getBookList();
                        int index = bookList.size()+1;
                                Book book = new Book(index,"第"+index+"个");
                                iBookManager.addBook(book);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        findViewById(R.id.bt_get_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iBookManager !=null){
                    try {
                        List<Book> bookList = iBookManager.getBookList();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            }
        });





    }










}