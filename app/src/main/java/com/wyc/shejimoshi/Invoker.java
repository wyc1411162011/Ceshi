package com.wyc.shejimoshi;
/**
 * 命令模式的调用者
 * @author Administrator
 *
 */
public class Invoker {
	CommandImp commandImp;
	public Invoker(CommandImp commandImp){
		this.commandImp=commandImp;
	}
	public void excuteCommand(){
		this.commandImp.excute();
	}
	public void unDoCommand(){
		this.commandImp.unDo();
	}
}
