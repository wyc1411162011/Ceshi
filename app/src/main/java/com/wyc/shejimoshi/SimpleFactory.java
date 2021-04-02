package com.wyc.shejimoshi;
/**
 * 简单工厂模式可以创建一个产品
 * 不符合 关闭原则，要是新加Car的时候要修改这个类，不符合
 * 关闭原则 ，扩展打开，修改关闭，所以引起第二种方法，工厂方法模式
 * @author Administrator
 *
 */
public class SimpleFactory {
	public static final int TYPE_BIKE=0;
	public static final int TYPE_BAOMA=1;
	public static ICar getCar(int type){
		ICar car=null;
		if(type==TYPE_BIKE){
			car=new Bike();
		}else if(type==TYPE_BAOMA){
			car=new BaomaCar();
		}
		return car;
	}
	//我个人感觉是简单工厂的另一种写法
	public static ICar getBaomaCar(){
		return new BaomaCar();
	}

	public static ICar getBikeCar(){
		return new Bike();
	}

}
