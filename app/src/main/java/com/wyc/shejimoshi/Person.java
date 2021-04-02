package com.wyc.shejimoshi;
//建造者模式里面创建的复杂的对象
//要把子部分 如head body 都创建一遍的时候才能算创建

public class Person {

	private String head;
	private String body;
	private String hand;
	private String leg;
	@Override
	public String toString() {
		return "Person [head=" + head + ", body=" + body + ", hand=" + hand
				+ ", leg=" + leg + "]";
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getHand() {
		return hand;
	}
	public void setHand(String hand) {
		this.hand = hand;
	}
	public String getLeg() {
		return leg;
	}
	public void setLeg(String leg) {
		this.leg = leg;
	}
}
