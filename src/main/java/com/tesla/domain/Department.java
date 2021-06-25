package com.tesla.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class Department {
    private Long id;
    private String name;
    private String sn;

    public String toJson() {
        return JSON.toJSONString(this);
    }
}