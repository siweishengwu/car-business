package com.tesla.domain;

import com.alibaba.fastjson.JSON;
import com.tesla.enums.AppointmentStatusEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class Appointment {
    /** 主键*/
    private Long id;

    /** 预约单流水号*/
    private String ano;

    /** 预约单状态 （预约中/履行中/消费中/归档/废弃单）   */
    //改为int类型 不能存空
    private int status;

    public String getStatusName(){
        //根据状态值返回状态的名称
        for (AppointmentStatusEnum value : AppointmentStatusEnum.values()) {
            if (this.status == value.getStatus()) {
                return value.getName();
            }
        }
        //没有找到
        return "未知";
    }

    /** 业务大类*/
//    private Long systemDictionaryId;
    private SystemDictionary systemDictionary;

    /** 预约说明*/
    private String info;

    /** 联系电话*/
    private String contactTel;

    /** 联系人名称*/
    private String contactName;

    /** 预约门店*/
//    private Long businessId;
    private Business business;

    /** 创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 预约时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointmentTime;

    public String toJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",this.id);
        map.put("businessId",this.business.getId());
        map.put("appointmentTime",new SimpleDateFormat("yyyy-MM-dd").format(this.appointmentTime));
        map.put("systemDictionaryId",this.systemDictionary.getId());
        map.put("contactName",this.contactName);
        map.put("contactTel",this.contactTel);
        map.put("info",this.info);
        return JSON.toJSONString(map);
    }
}