package com.tesla.domain;

import com.tesla.enums.ConsumptionStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Consumption {
    /** 主键*/
    private Long id;

    /** 消费单流水号*/
    private String cno;

    /** 消费单状态 （待结算/待审核/归档/坏账）*/
    //默认为0 基本数据类型
    private int status;

    public String getStatusName(){
        for (ConsumptionStatusEnum statusEnum : ConsumptionStatusEnum.values()) {
            if (statusEnum.getStatus() == this.status) {
                return statusEnum.getName();
            }
        }
        return "未知";
    }

    /** 总消费金额*/
    private BigDecimal totalAmount = BigDecimal.ZERO;

    /** 实收金额*/
    private BigDecimal payAmount;

    /** 优惠金额*/
    private BigDecimal discountAmount;

    /** 结算备注*/
    private String info;

    /** 结算时间*/
    private Date payTime;

    /** 结算人*/
    private Employee payee;

    /** 客户名称*/
    private String customerName;

    /** 客户联系方式*/
    private String customerTel;

    /** 车牌信息记录*/
    private String carLicence;

    /** 车型记录*/
    private String carType;

    /** 关联预约单*/
    private String appointmentAno;

    /** 进店时间*/
    private Date checkinTime;

    /** 离店时间*/
    private Date checkoutTime;

    /** 消费门店*/
    private Business business;

    /** 创建时间*/
    private Date createTime;

    /** 创建人*/
    private Employee createUser;

    /** 审核时间*/
    private Date auditTime;

    /** 审核人*/
    private Employee auditor;

}