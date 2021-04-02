package com.wyc.shejimoshi;
/**
 * 具体的命令对象持有一个receiver
 * @author Administrator
 *
 */
public class ConcreteCommand implements CommandImp{
	private Reciver reciver;
	public ConcreteCommand(Reciver reciver){
		this.reciver=reciver;
	}
	@Override
	public void excute() {
		reciver.action();
	}

	@Override
	public void unDo() {
		reciver.unAction();
	}

}
