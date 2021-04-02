package com.wyc.shejimoshi;

public class BikeFactory implements IFactory{

	@Override
	public ICar getCar() {
		// TODO Auto-generated method stub
		return new Bike();
	}

}
