package com.wyc.shejimoshi;
/**
 * 具体的策略类角色
 *
 * @author Administrator
 *
 */
public class MemberChujiStrategy implements AbstractMemberStrategy{

	@Override
	public double calculatePrice(double price) {
		// TODO Auto-generated method stub
		System.out.println("初级的用户没有优惠");
		return price;
	}

}
