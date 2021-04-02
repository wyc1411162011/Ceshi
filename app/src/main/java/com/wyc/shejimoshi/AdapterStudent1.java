package com.wyc.shejimoshi;

//对象适配器模式就是把源角色的对象传递过来，我感觉这里面能传递多个对象
//用之前的N个类
//实现目标的接口
//这里准确来说是依赖源源角色 其实并不一定算是Person 只是要依赖里面的方法而已
public class AdapterStudent1 implements TargetStudent{
	AdapteePerson person;
	public AdapterStudent1(AdapteePerson person){
		this.person=person;
	}

	@Override
	public void study() {
		// TODO Auto-generated method stub
		System.out.println(" 对象适配器学生 在学习");

	}

	@Override
	public void listenToTeacherSay() {
		// TODO Auto-generated method stub
		System.out.println("对象适配器 学生 在听老师说话");
	}

	@Override
	public void say() {
		// TODO Auto-generated method stub
		person.say();
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		person.eat();
	}

}
