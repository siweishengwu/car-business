package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.AppointmentTime;
import com.tesla.mapper.AppointmentTimeMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IAppointmentTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTimeServiceImpl implements IAppointmentTimeService {
    @Autowired
    private AppointmentTimeMapper appointmentTimeMapper;

    @Override
    public void save(AppointmentTime appointmentTime) {
        appointmentTimeMapper.insert(appointmentTime);
    }

    @Override
    public void update(AppointmentTime appointmentTime) {
        appointmentTimeMapper.updateByPrimaryKey(appointmentTime);
    }

    @Override
    public void delete(Long id) {
        appointmentTimeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AppointmentTime get(Long id) {
        return appointmentTimeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AppointmentTime> listAll() {
        return appointmentTimeMapper.selectAll();
    }

    @Override
    public PageInfo<AppointmentTime> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<AppointmentTime> appointmentTimes = appointmentTimeMapper.selectForList(qo);
        return new PageInfo(appointmentTimes);
       }
}
