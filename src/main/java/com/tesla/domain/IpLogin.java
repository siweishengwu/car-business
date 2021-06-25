package com.tesla.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class IpLogin {
    /** */
    private Long id;

    /** */
    private String username;

    /** */
    private String ip;

    /** */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

}