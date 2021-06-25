package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Appointment;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IAppointmentService {
    void save(Appointment appointment, String ip);
    void update(Appointment appointment);
    void delete(Long id);
    Appointment get(Long id);
    List<Appointment> listAll();

    PageInfo<Appointment> query(QueryObject qo);

    void updateStatus(Appointment appointment);
}
