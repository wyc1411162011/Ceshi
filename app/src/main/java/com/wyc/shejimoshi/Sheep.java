package com.wyc.shejimoshi;

import java.sql.Date;
//这个是浅层克隆
public class Sheep implements Cloneable{
	public int age;
	public String name;
	public Date date;
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		Sheep qianSheep=new Sheep();
		qianSheep.age=12;
		qianSheep.name="少林";
		Date date=new Date(1234);
		qianSheep.date=date;
		Sheep newQianSheep=(Sheep) qianSheep.clone();
		date.setTime(System.currentTimeMillis());
		qianSheep.name="武当";
		System.out.println(qianSheep.age+" --"+qianSheep.name+"  "+qianSheep.date);
		System.out.println(newQianSheep.age+" --"+newQianSheep.name+"  "+newQianSheep.date);
	}

}
