package com.wyc.shejimoshi;

public class HighPersonFactory implements IAbstractFactory{

	@Override
	public ICar getCar() {
		// TODO Auto-generated method stub
		return new BaomaCar();
	}

	@Override
	public IBreakfast getBreakfast() {
		// TODO Auto-generated method stub
		return new Milk();
	}

}
