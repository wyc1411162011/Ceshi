package com.yonyou.callback;

import com.yonyou.entity.Account;

public interface MCallback {
     public void onSuccess(Account account);
     public  void onFailed();
 }