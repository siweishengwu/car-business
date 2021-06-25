package com.tesla.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AppointmentTime {
    /** */
    private Long id;

    /** */
    private String phone;

    /** */
    private String ip;

    /** */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date day;

    /** */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hour;
}