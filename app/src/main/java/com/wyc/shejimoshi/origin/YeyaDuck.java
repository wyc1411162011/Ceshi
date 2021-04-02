package com.wyc.shejimoshi.origin;

public class YeyaDuck extends Duck{
	public YeyaDuck(){
		quackBehavior=new Quack();
		flyBehavior=new FlyWithWings();
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("野鸭的长相");
	}


}
