package com.tesla.mapper;

import com.tesla.domain.Appointment;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface AppointmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Appointment record);

    Appointment selectByPrimaryKey(Long id);

    List<Appointment> selectAll();

    int updateByPrimaryKey(Appointment record);

    List<Appointment> selectForList(QueryObject qo);

    void updateStatus(Appointment appointment);
}