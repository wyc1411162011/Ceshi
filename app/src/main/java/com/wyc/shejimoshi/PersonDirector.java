package com.wyc.shejimoshi;
/**
 * 建造者模式的指导者角色
 * 这个里面表示的是对象的创建过程，并不管实现
 * @author Administrator
 *
 */
public class PersonDirector {
	//构建一个人 这个是以builder 为对象 这是传递过来的
	//不同的调用方式可能创建不同的对象 或者 少一个两个方法也会不一样

	public Person constructPerson(PersonBuilderImp builder){
		builder.buildBody();
		builder.buildHand();
		builder.buildHead();
		builder.buildLeg();
		Person person=builder.createPerson();
		return person;
	}
}
