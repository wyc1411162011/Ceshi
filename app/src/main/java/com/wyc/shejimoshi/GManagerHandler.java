package com.wyc.shejimoshi;

public class GManagerHandler extends AbstractHandler{

    @Override
    public void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays()<=MAX){
            System.out.println("总经理:已审批 请假申请通过");
            return;
        }
         //如果大于30天的，根据具体的需要进行处理
        if (request.getLeaveDays()> MAX){

        }
    }
}
