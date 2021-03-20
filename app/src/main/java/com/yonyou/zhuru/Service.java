package com.yonyou.zhuru;

class Service {
    @Inject
    private DAO dao;
 
    public void doSth() {
        System.out.println(dao.getInfo());
    }
 
    public static void main(String[] args) {
        Container.getBean(Service.class).doSth();
    }
}