package com.wyc.shejimoshi;

public class BaomaFactory implements IFactory{

	@Override
	public ICar getCar() {
		// TODO Auto-generated method stub
		return new BaomaCar();
	}

}
