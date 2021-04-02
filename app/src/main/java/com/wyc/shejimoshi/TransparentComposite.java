package com.wyc.shejimoshi;

import java.util.ArrayList;
import java.util.List;

public class TransparentComposite extends AbstractTransparentComponent{
	private List<AbstractTransparentComponent>componentList=new ArrayList<AbstractTransparentComponent>();
	public TransparentComposite(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showStructure(String str) {
		// TODO Auto-generated method stub
		System.out.println(str+"--"+getName());
		if(getChilds()!=null &&getChilds().size()>0){
			str=str+"  ";//下一级的时候隔开
			for(AbstractTransparentComponent component:getChilds()){
				component.showStructure(str);
			}
		}
	}
	@Override
	public void addChild(AbstractTransparentComponent component){
		componentList.add(component);
	}
	@Override
	public void removeChild(int index){
		componentList.remove(index);
	}
	@Override
	public List<AbstractTransparentComponent>getChilds(){
		return componentList;
	}

}
