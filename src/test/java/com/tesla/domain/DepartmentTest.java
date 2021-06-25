package com.tesla.domain;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Test
    public void toJson() {
        Department department = new Department();
        System.out.println(department.toJson());
    }

    @Test
    public void testDatetime(){
        Date createTime = new Date();
        String datetimeString = new SimpleDateFormat("yyyyMMddHHmmss").format(createTime);

        System.out.println("datetimeString = " + datetimeString);
        System.out.println((int) (Math.random()*100000)); //[0,1)

        System.out.println(DateUtil.format(createTime, "yyyyMMddHHmmss"));
        System.out.println(RandomUtil.randomNumbers(5));
    }
}