package com.tesla.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ConsumptionItem {
    /** 主键*/
    private Long id;

    /** 业务大类*/
    private SystemDictionary category;

    /** 业务小类*/
    private SystemDictionary categoryItem;

    /** 结算类型*/
    private SystemDictionary payType;

    /** 应收金额*/
    private BigDecimal amount;

    /** 实收金额*/
    private BigDecimal payAmount;

    /** 优惠金额*/
    private BigDecimal discountAmount;

    /** 创建人*/
    private Long createUserId;

    /** 创建时间*/
    private Date createTime;

    /** 结算单流水号*/
    private String cno;

    public String toJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",this.id);
        map.put("category",this.category.getName());
        map.put("categoryItem",this.categoryItem.getName());
        map.put("payType",this.payType.getName());
        map.put("amount",this.amount);
        map.put("payAmount",this.payAmount);
        map.put("discountAmount",this.discountAmount);
        map.put("createUserId",this.createUserId);
        map.put("cno",this.cno);
        return JSON.toJSONString(map);
    }

}