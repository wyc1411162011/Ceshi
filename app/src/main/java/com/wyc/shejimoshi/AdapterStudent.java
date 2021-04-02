package com.wyc.shejimoshi;

//类的适配器模式
//实现目标的接口
//这里准确来说是依赖源源角色 其实并不一定算是Person 只是要依赖里面的方法而已
public class AdapterStudent extends AdapteePerson implements TargetStudent{

	@Override
	public void study() {
		// TODO Auto-generated method stub
		System.out.println(" 学生 在学习");

	}

	@Override
	public void listenToTeacherSay() {
		// TODO Auto-generated method stub
		System.out.println(" 学生 在听老师说话");
	}

}
