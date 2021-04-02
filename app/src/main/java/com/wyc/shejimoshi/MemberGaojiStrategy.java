package com.wyc.shejimoshi;

public class MemberGaojiStrategy implements AbstractMemberStrategy{

	@Override
	public double calculatePrice(double price) {
		// TODO Auto-generated method stub
		System.out.println("高级的用户打五折");
		return price*0.5;
	}

}
