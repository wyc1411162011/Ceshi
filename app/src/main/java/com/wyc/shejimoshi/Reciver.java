package com.wyc.shejimoshi;
//命令模式的接受者
public class Reciver {
	public void action(){
		System.out.println("命令模式执行一个命令");
	}
	public void unAction(){
		System.out.println("命令cancel一个命令");
	}
}
