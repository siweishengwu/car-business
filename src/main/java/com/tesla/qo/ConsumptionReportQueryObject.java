package com.tesla.qo;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//封装报表结算单 查询的参数
//拼接到where子句
//拼接到group by 和 select 子句$
@Data
public class ConsumptionReportQueryObject extends QueryObject {
    private String groupType = "shop";//默认根据门店查询
    private Long businessId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    private Boolean appointmentType;

    private static Map<String,String> groupTypeMap = new HashMap<>();
    static {
        groupTypeMap.put("shop","b.name");
        groupTypeMap.put("year","DATE_FORMAT(c.create_time,'%Y')");
        groupTypeMap.put("month","DATE_FORMAT(c.create_time,'%Y-%m')");
        groupTypeMap.put("day","DATE_FORMAT(c.create_time,'%Y-%m-%d')");
    }

    public String getGroupTypeName(){
        return groupTypeMap.get(this.groupType);
    }

    //sql取值用
    public Date getEndCreateTime(){
        if (this.endCreateTime != null) {
            return DateUtil.endOfDay(this.endCreateTime);
        }else {
            return null;
        }
    }
}
