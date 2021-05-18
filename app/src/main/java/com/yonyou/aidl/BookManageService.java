package com.yonyou.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by ufsoft on2021/5/13
 * describle:
 */
public class BookManageService extends Service {
    private List<Book>bookList = new ArrayList<>();
    private Binder binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.e("tag","getBookList  当前的线程"+Thread.currentThread().getName());
            return bookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.e("tag","addBook 当前的线程"+Thread.currentThread().getName());
            bookList.add(book);
        }

        @Override
        public List<Book> addAndGetBook(Book book) throws RemoteException {
            bookList.add(book);
            Log.e("tag","addAndGetBook 当前的线程"+Thread.currentThread().getName());
            return bookList;
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
