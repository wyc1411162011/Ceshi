package com.yonyou.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ufsoft on2021/3/15
 * describle:
 */
public class MyBroadCastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("tag","收到消息"+intent.getStringExtra("data"));
    }

}
