package com.tesla.qo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ConsumptionQueryObject extends QueryObject {
    private Integer status;
    private Long businessId;
    private Boolean appointmentType; //0 false 非预约 ; 1 true 预约
    private String customerName;
    private String customerTel;
    private String AppointmentAno;
    private String cno;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginPayTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endPayTime;
}
