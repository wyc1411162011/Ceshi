package com.wyc.shejimoshi.origin;


public abstract class Duck {
	//这个时候体现了多用 组合少用继承的思想  组合这种行为可能有可能没有
	//并且可以多变 动态生成，不用固定死继承的话就写死了，子类不管有没有需要不要这个方法行为都会有这个方法
	//可能都要重置
	public FlyBehavior flyBehavior;
	public QuackBehavior quackBehavior;
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
	public void performFly(){
		flyBehavior.fly();
	}
	public void performQuack(){
		quackBehavior.quack();
	}
	public void swim(){
		System.out.println("鸭子在游泳");
	}
	public abstract void display();
}
