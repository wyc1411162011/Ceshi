package com.wyc.shejimoshi;
//这个是自己项目中用到的 这个是抽象类，因为用不到接口的其他两个方法，所以
//新建一个抽象类，把其他的两个方法置空什么也不做，把用到的那个方法弄成抽象的
//必须复写
public abstract class AbstractRequestSuccessListener implements RequestListener{

	@Override
	public abstract void onSuccess(String message) ;

	@Override
	public void onFail() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNetError() {
		// TODO Auto-generated method stub

	}

}
