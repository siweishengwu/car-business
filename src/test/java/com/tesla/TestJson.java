package com.tesla;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tesla.domain.Department;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TestJson {
    @Test
    public void jackJson() throws IOException {
        Department department = new Department();
        department.setId(1L);
        department.setName("开发部");
        department.setSn("DEV");

        ObjectMapper objectMapper = new ObjectMapper();
        //Java 对象转JSON
        System.out.println(objectMapper.writeValueAsString(department));

        String jsonStr = "{\"id\":5,\"name\":\"开发部\"}";
        System.out.println(objectMapper.readValue(jsonStr, Department.class));
    }

    @Test
    public void fastJson(){
        Department department = new Department();
        department.setId(1L);
        department.setName("开发部");
        department.setSn("DEV");

        //Java 对象转JSON
        System.out.println(JSON.toJSONString(department));
        //JSON 转 Java 对象

        String jsonStr = "{\"id\":5,\"name\":\"开发部\"}";
        System.out.println(JSON.parseObject(jsonStr, Department.class));
    }

    @Test
    public void gson(){
        String strings = "[1,2,3,51,52,53]";
        System.out.println(strings);
        Gson gson = new Gson();
        Long[] longs = gson.fromJson(strings, Long[].class);
        Arrays.toString(longs);
    }

    @Test
    public void time(){
        Date date = new Date();
        System.out.println(date);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.HOUR,1);
        System.out.println(instance.getTime());

        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.add(Calendar.DATE,1);
        System.out.println(day.getTime());
    }
}
