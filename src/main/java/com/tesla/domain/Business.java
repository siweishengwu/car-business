package com.tesla.domain;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Business {
    /** 主键*/
    private Long id;

    /** 门店名称*/
    private String name;

    /** 门店介绍*/
    private String intro;

    /** 经营范围*/
    private String scope;

    /** 门店电话*/
    private String tel;

    /** 门店地址*/
    private String address;

    /** 经营日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date openDate;

    /** 营业执照图片*/
    private String licenseImg;

    /** 营业执照号码*/
    private String licenseNumber;

    /** 法人姓名*/
    private String legalName;

    /** 法人电话*/
    private String legalTel;

    /** 法人身份证*/
    private String legalIdcard;

    /** 门店性质(总店/分店)*/
    private boolean mainStore;
}