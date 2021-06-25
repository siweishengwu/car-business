package com.tesla.enums;


public enum ConsumptionStatusEnum {
    CONSUME("待结算", 0),
    AUDIT("待审核", 1),
    FINISH("归档", 2),
    FAILURE("坏账", 3);

    private String name; //状态名称
    private int status;  //状态对应的数值

    //枚举默认private

    ConsumptionStatusEnum(String name, int status){
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
