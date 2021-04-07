package com.yonyou.contentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

public class ContentProviderDemoActivity extends BaseActivity {
    private static final String AUTHORITY = "com.jrmf360.studentProvider";
    private static final Uri STUDENT_URI = Uri.parse("content://" + AUTHORITY + "/student");
    private ContentResolver contentResolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_demo);
        contentResolver = getContentResolver();
        contentResolver.registerContentObserver(STUDENT_URI, true, new MyContentObserver(new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        }));
        findViewById(R.id.bt_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = contentResolver.query(STUDENT_URI, null, null, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                        String desc = cursor.getString(cursor.getColumnIndex("desc"));
                        Log.e(getClass().getSimpleName(), "Student:" + "name = " + name + ",age = " + age + ",desc = " + desc);
                    }
                    cursor.close();
                }
            }
        });
    }

    public class MyContentObserver extends ContentObserver {

        Handler mHandler;
        public MyContentObserver(Handler handler) {
            super(handler);
            mHandler = handler;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            Log.e("tag","监听方法执行....");
            Message message = Message.obtain();
            message.obj = uri;
            mHandler.sendMessage(message);
        }
    }
}