package com.wyc.shejimoshi;
//没有任何的其实
public class LowPersonFactory implements IAbstractFactory{

	@Override
	public ICar getCar() {
		// TODO Auto-generated method stub
		return new Bike();
	}

	@Override
	public IBreakfast getBreakfast() {
		// TODO Auto-generated method stub
		return new Orange();
	}

}
