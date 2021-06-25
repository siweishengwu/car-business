package com.tesla.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class SystemDictionary {
    private Long id;
    private String name;
    private String sn;
    private String intro;

    private Long parentId;
    //专门用来存这个数据字典子项
    private List<SystemDictionary> items = new ArrayList<>();

    public String toJson(){
        Map<String,Object> data = new HashMap<>();
        data.put("id",this.id);
        data.put("name",this.name);
        data.put("sn",this.sn);
        data.put("intro",this.intro);
        data.put("parentId",this.parentId);
        return JSON.toJSONString(data);
    }
}