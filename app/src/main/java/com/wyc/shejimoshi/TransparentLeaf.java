package com.wyc.shejimoshi;

public class TransparentLeaf extends AbstractTransparentComponent{

	public TransparentLeaf(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showStructure(String str) {
		// TODO Auto-generated method stub
		System.out.println(str+"--"+getName());
	}

}
