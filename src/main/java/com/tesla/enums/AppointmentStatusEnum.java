package com.tesla.enums;


public enum AppointmentStatusEnum {
    PEND("待确认", 0),
    PERFORM("履行中", 1),
    CONSUME("消费中", 2),
    FINISH("归档", 3),
    FAILURE("废弃", 4);

    private String name; //状态名称
    private int status;  //状态对应的数值

    //枚举默认private

    AppointmentStatusEnum(String name, int status){
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
