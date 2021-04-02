package com.wyc.shejimoshi;
/**
 * 具体的建造者角色负责 person的初始化实现以及返回
 * ：实现Builder接口，针对不同的商业逻辑，具体化复杂对象的各部分的创建。 在建造过程完成后，提供产品的实例。
 * @author Administrator
 *
 */
public class SmartPersonBuilder implements PersonBuilderImp{
	private Person person;
	public SmartPersonBuilder(){
		person=new Person();
	}
	@Override
	public void buildHead() {
		// TODO Auto-generated method stub
		person.setHead("聪明的大脑智商180");
	}

	@Override
	public void buildBody() {
		// TODO Auto-generated method stub
		person.setBody("普通的身体");
	}

	@Override
	public void buildHand() {
		// TODO Auto-generated method stub
		person.setHand("普通的手");
	}

	@Override
	public void buildLeg() {
		// TODO Auto-generated method stub
		person.setLeg("普通的腿");
	}

	@Override
	public Person createPerson() {
		// TODO Auto-generated method stub
		return person;
	}

}
