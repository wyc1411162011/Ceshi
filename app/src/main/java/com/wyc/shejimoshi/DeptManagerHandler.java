package com.wyc.shejimoshi;

public class DeptManagerHandler extends  AbstractHandler{
    @Override
    public void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays()<=MIDDLE){
            System.out.println("部门经理:已审批 请假申请通过");
            return;
        }
        //请假天数大于3天，需要继续传递到总经理进行审批
        if (request.getLeaveDays()> MIDDLE){
            if(nextHandler!=null){
                System.out.println("部门经理:已审批 还需要总经理审批");
                nextHandler.handlerRequest(request);
            }
        }
    }
}