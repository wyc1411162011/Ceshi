package com.wyc.shejimoshi;
//第一种设计模式
public class Sigleton {
	//懒汉式单例模式  3种
	private static volatile Sigleton instance;
	//第一种懒汉式设计模式
	//只有需要的时候才会创建实例线程不安全的，不推荐使用
	public static Sigleton getInstance1(){
		if(instance==null){
			instance=new Sigleton();
		}
		return instance;
	}
	//第二种线程加锁的，太耗时了，线程安全是安全，但是太耗时间
	//这种简单粗暴的方式不合理，不管他是不是
	//这种引起的第三种 不推荐使用
	public static synchronized Sigleton getInstance2(){
		if(instance==null){
			instance=new Sigleton();
		}
		return instance;
	}
	
	public static  Sigleton getInstance3(){
		if(instance==null){
			synchronized(Sigleton.class){
				//这里面会执行一次第一次instance为空的时候，之后就不会有这种情况了
				//并且进来之后锁定之后也要判断
				//可能两个线程同时访问  都执行到第一个步骤的instance==null的时候
				if(instance==null){
					instance=new Sigleton();
				}
			}
			
		}
		return instance;
	}
	
	
	private static Sigleton instance4=new Sigleton();
	//最简单恶汉方式但是会浪费内存，不管有没有都会创建一个
	//所以弄出加强版的恶汉模式 第五个线程安全，建议使用
	public static  Sigleton getInstance4(){
		return instance4;
	}
	
	
	//第五种只有创建的时候才会使用，并且是线程安全的建议使用
	//既然一个静态类直接就创建了这个实例，那就新加一个静态类的静态对象只有调用这个方法的时候才会创建
	public static  Sigleton getInstance5(){
		return SigletonHolder.sigleton;
	}
	private static class SigletonHolder{
		private static Sigleton sigleton=new Sigleton();
	}
	
	
}
