package com.wyc.shejimoshi;

public abstract class AbstractHandler {
    /**
     * 需主管审批的天数
     */
    protected final static int  MIN=1;

    /**
     * 需部门经理审批的天数
     */
    protected final static int MIDDLE=3;

    /**
     * 需总经理历审批的天数
     */
    protected  final static int MAX=30;


    protected AbstractHandler nextHandler;

    /**
     * 设置下一个审批者
     * @param nextHandler
     */
    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 需要子类进行重写
     * @param request
     */
    public abstract void handlerRequest(LeaveRequest request);
}
