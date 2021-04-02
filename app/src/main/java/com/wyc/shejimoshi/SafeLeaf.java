package com.wyc.shejimoshi;
/**
 * 合成模式 部分的描述
 * @author Administrator
 * 叶子构件角色没有下级的对象，这个是原始的对象
 */
public class SafeLeaf implements ISafeCompnComponent{
	private String name;
	public SafeLeaf(String name){
		this.name=name;
	}
	@Override
	public void showStructure(String str) {
		System.out.println(str+"--"+name);

	}

}
