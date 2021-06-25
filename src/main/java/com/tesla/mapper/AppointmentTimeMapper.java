package com.tesla.mapper;

import com.tesla.domain.AppointmentTime;
import com.tesla.qo.QueryObject;

import java.util.Date;
import java.util.List;

public interface AppointmentTimeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppointmentTime record);

    AppointmentTime selectByPrimaryKey(Long id);

    List<AppointmentTime> selectAll();

    int updateByPrimaryKey(AppointmentTime record);

    List<AppointmentTime> selectForList(QueryObject qo);

    Integer queryIp(String ip);

    Date queryHourTime(String ip);
}