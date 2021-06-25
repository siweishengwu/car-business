package com.tesla.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Appointment;
import com.tesla.domain.AppointmentTime;
import com.tesla.mapper.AppointmentMapper;
import com.tesla.mapper.AppointmentTimeMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class
AppointmentServiceImpl implements IAppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private AppointmentTimeMapper appointmentTimeMapper;

    @Override
    public void save(Appointment appointment, String ip) {
        //生成预约单号
        //设置创建时间
        Date createTime = new Date();
        Integer ipCount = appointmentTimeMapper.queryIp(ip);
        Date HourTime = appointmentTimeMapper.queryHourTime(ip);
//        //次数不为10以内
//        if (!(ipCount <= 10)){
//            return;
//        }
//        if(HourTime == null || createTime.before(HourTime)){
//
//        }
        //创建日,等等+1日
        Calendar day = Calendar.getInstance();
        day.setTime(createTime);
        day.add(Calendar.DATE,1);
        //创建时,等等+1时
        Calendar hour = Calendar.getInstance();
        hour.setTime(createTime);
        hour.add(Calendar.HOUR,1);

        //创建对象 插入数据
        AppointmentTime appointmentTime = new AppointmentTime();

        appointmentTime.setIp(ip);
        appointmentTime.setPhone(appointment.getContactTel());
        appointmentTime.setDay(day.getTime());
        appointmentTime.setHour(hour.getTime());
        appointmentTimeMapper.insert(appointmentTime);

        appointment.setAno(DateUtil.format(createTime, "yyyyMMddHHmmss")+ RandomUtil.randomNumbers(5));
        appointment.setCreateTime(createTime);
        appointmentMapper.insert(appointment);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentMapper.updateByPrimaryKey(appointment);
    }

    @Override
    public void delete(Long id) {
        appointmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Appointment get(Long id) {
        return appointmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Appointment> listAll() {
        return appointmentMapper.selectAll();
    }

    @Override
    public PageInfo<Appointment> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"a.status,a.appointment_time");
        List<Appointment> appointments = appointmentMapper.selectForList(qo);
        return new PageInfo(appointments);
       }

    @Override
    public void updateStatus(Appointment appointment) {
        appointmentMapper.updateStatus(appointment);
    }
}
