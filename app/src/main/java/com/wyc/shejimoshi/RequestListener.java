package com.wyc.shejimoshi;

public interface RequestListener {
	public void onSuccess(String message);
	public void onFail();
	public void onNetError();
}
