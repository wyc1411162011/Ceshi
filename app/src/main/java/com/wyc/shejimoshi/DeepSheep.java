package com.wyc.shejimoshi;

import java.sql.Date;
//深度拷贝
public class DeepSheep implements Cloneable{
	public int age;
	public String name;
	public Date date;
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Object object=super.clone();
		DeepSheep sheep=(DeepSheep)object;
		sheep.date=(Date) this.date.clone();
		return object;
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		DeepSheep qianDeepSheep=new DeepSheep();
		qianDeepSheep.age=12;
		qianDeepSheep.name="少林";
		Date date=new Date(1234);
		qianDeepSheep.date=date;
		DeepSheep newQianDeepSheep=(DeepSheep) qianDeepSheep.clone();
		date.setTime(System.currentTimeMillis());
		qianDeepSheep.name="武当";
		System.out.println(qianDeepSheep.age+" --"+qianDeepSheep.name+"  "+qianDeepSheep.date);
		System.out.println(newQianDeepSheep.age+" --"+newQianDeepSheep.name+"  "+newQianDeepSheep.date);
	}

}
