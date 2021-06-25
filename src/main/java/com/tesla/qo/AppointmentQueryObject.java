package com.tesla.qo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AppointmentQueryObject extends QueryObject {
    private String ano;
    private Long systemDictionaryId;
    private Integer status;
    private Long businessId;
    private String contactName;
    private String contactTel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginAppointmentTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endAppointmentTime;
}
