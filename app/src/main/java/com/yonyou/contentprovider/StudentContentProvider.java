package com.yonyou.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.yonyou.tool.Util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentContentProvider extends ContentProvider {

    //这里的AUTHORITY就是我们在AndroidManifest.xml中配置的authorities
    private static final String AUTHORITY = "com.jrmf360.studentProvider";

    //匹配成功后的匹配码
    private static final int MATCH_CODE = 100;

    private static UriMatcher uriMatcher;


    //数据改变后指定通知的Uri
    private static final Uri NOTIFY_URI = Uri.parse("content://" + AUTHORITY + "/student");

    static {
        //匹配不成功返回NO_MATCH(-1)
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //添加我们需要匹配的uri
        uriMatcher.addURI(AUTHORITY,"student", MATCH_CODE);
    }


    @Override
    public boolean onCreate() {
        Util.print(this.getClass(),"----------");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int match = uriMatcher.match(uri);
        if (match == MATCH_CODE){
            Util.print(this.getClass(),"match  ");
            notifyChange();
        }
        Util.print(this.getClass(),"");
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == MATCH_CODE){
            Util.print(this.getClass(),"insert match ");
            notifyChange();
        }
        Util.print(this.getClass(),"");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == MATCH_CODE){
            Util.print(this.getClass(),"deletematch ");
            notifyChange();
        }
        Util.print(this.getClass(),"");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

    private void notifyChange(){
        getContext().getContentResolver().notifyChange(NOTIFY_URI,null);
    }
}
