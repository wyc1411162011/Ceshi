package com.wyc.shejimoshi.origin;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Duck duck=new YeyaDuck();
			duck.performFly();
			duck.performQuack();
			duck.setQuackBehavior(new ZhizhijiaoQuack());
			duck.performQuack();
	}

}
