package com.wyc.shejimoshi;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象透明的构件角色 包含 树枝和叶子里面所有的方法
 * 我在这里是用的所有的叶子构件的角色的所有功能，树枝构件角色的所有功能都要重写方法
 * @author Administrator
 *
 */
public abstract class AbstractTransparentComponent {
	private String name;
	public AbstractTransparentComponent(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

	public abstract void showStructure(String str) ;
	public void addChild(AbstractTransparentComponent component){
		throw new UnsupportedOperationException("对象不支持此操作");
	}
	public void removeChild(int index){
		throw new UnsupportedOperationException("对象不支持此操作");
	}
	public List<AbstractTransparentComponent>getChilds(){
		throw new UnsupportedOperationException("对象不支持此操作");
	}
}
