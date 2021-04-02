package com.wyc.shejimoshi;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝构件角色负责管理下一级组件
 *
 */
public class SafeComposite implements ISafeCompnComponent{
	private String name;
	private List<ISafeCompnComponent>componentList=new ArrayList<ISafeCompnComponent>();
	public SafeComposite(String name){
		this.name=name;
	}
	@Override
	public void showStructure(String str) {
		System.out.println(str+"--"+name);
		if(componentList!=null &&componentList.size()>0){
			str=str+"  ";//下一级的时候隔开
			for(ISafeCompnComponent component:componentList){
				component.showStructure(str);
			}
		}
	}
	public void addChild(ISafeCompnComponent component){
		componentList.add(component);
	}
	public void removeChild(int index){
		componentList.remove(index);
	}
	public List<ISafeCompnComponent>getChilds(){
		return componentList;
	}

}
