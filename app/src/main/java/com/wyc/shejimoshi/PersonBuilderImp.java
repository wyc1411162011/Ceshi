package com.wyc.shejimoshi;
/**
 * 创建者模式抽象的 创建者角色 用户创建和表示的分离的表示
 * 规定要有什么方法来初始化对象什么的
 *  对复杂对象的创建过程加以抽象，通过一个抽象接口来规范复杂对象的各个组成部分的构建；该接口规定了要实现复杂对象的各个部分的构建，不涉及具体对象的具体的构建
 * 给出一个抽象接口，以规范产品对象的各个组成成分的建造。这个接口规定要实现复杂对象的哪些部分的创建，并不涉及具体的对象部件的创建
 *
 */
public interface PersonBuilderImp {
	public void buildHead();
	public void buildBody();
	public void buildHand();
	public void buildLeg();
	public Person createPerson();
}
