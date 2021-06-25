package com.tesla.qo;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BusinessQueryObject extends QueryObject{
    private String name;
    private String scope;
    private String tel;
    private String legalName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginOpenDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endOpenDate;
}
