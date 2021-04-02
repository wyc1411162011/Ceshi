package com.wyc.shejimoshi;
//这个是在这个方法里面使用这个
//策略模式的角色类原来 AbstractMemberStrategy里面的功能是 MemberPriceContext
//后来总变话所以单独抽象出来
public class MemberPriceContext {
	AbstractMemberStrategy strategy;
	public MemberPriceContext(AbstractMemberStrategy strategy){
		this.strategy=strategy;
	}
	public double jisuanPrice(double price){
		//其实真正的是这个里面使用他
		double currentPrice=strategy.calculatePrice(price);
		return currentPrice;
	}
	public void print(){
		System.out.println("打印");
	}
}
