package com.tesla.domain;


import com.tesla.mapper.AppointmentTimeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Time {
    @Autowired
    private AppointmentTimeMapper appointmentTimeMapper;

    @Test
    public void Test(){

        String ip = "127.0.0.1";
        Integer ipCount = appointmentTimeMapper.queryIp(ip);
        System.out.println("ipCount = " + ipCount);
        Date HourTime = appointmentTimeMapper.queryHourTime(ip);
        System.out.println("HourTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(HourTime));
//        Date DayTime = appointmentTimeMapper.queryDayTime(ip);
    }
}
