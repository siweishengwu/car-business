package com.tesla.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Appointment;
import com.tesla.domain.Consumption;
import com.tesla.domain.ConsumptionItem;
import com.tesla.enums.AppointmentStatusEnum;
import com.tesla.mapper.AppointmentMapper;
import com.tesla.mapper.ConsumptionMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IConsumptionService;
import com.tesla.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements IConsumptionService {
    @Autowired
    private ConsumptionMapper consumptionMapper;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public void save(Consumption consumption) {
        consumptionMapper.insert(consumption);
    }

    @Override
    public void update(Consumption consumption) {
        consumptionMapper.updateByPrimaryKey(consumption);
    }

    @Override
    public void delete(Long id) {
        consumptionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Consumption get(Long id) {
        return consumptionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Consumption> listAll() {
        return consumptionMapper.selectAll();
    }

    @Override
    public PageInfo<Consumption> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"c.status,c.create_Time");
        List<Consumption> consumptions = consumptionMapper.selectForList(qo);
        return new PageInfo(consumptions);
       }

    @Override
    public Consumption save(Long appointmentId) {
        //有消费,本质就是把对应预约改成消费中状态,
        //根据id查询预约
        Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentId);
        appointment.setStatus(AppointmentStatusEnum.CONSUME.getStatus());
        appointmentMapper.updateStatus(appointment);

        //还要往数据库结算单表中添加一条数据,结算单的状态是待结算状态
        Consumption consumption = new Consumption();
        Date now = new Date();
        consumption.setCreateTime(now);
        consumption.setCheckinTime(now);
        consumption.setCreateUser(UserContext.getEmployee());
        consumption.setCustomerName(appointment.getContactName());
        consumption.setCustomerTel(appointment.getContactTel());
        consumption.setBusiness(appointment.getBusiness());
        consumption.setAppointmentAno(appointment.getAno());
        consumption.setCno(DateUtil.format(now,"yyyyMMddHHmmss"+ RandomUtil.randomNumbers(5)));
//  `cno` varchar(32) DEFAULT NULL COMMENT '消费单流水号',
//  `customer_name` varchar(32) DEFAULT NULL COMMENT '客户名称',
//  `customer_tel` varchar(32) DEFAULT NULL COMMENT '客户联系方式',
//  `appointment_ano` varchar(20) DEFAULT NULL COMMENT '关联预约单',
//  `checkin_time` datetime DEFAULT NULL COMMENT '进店时间',
//  `business_id` bigint(20) DEFAULT NULL COMMENT '消费门店',
//  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
//  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
        consumptionMapper.insert(consumption);
        return consumption;
    }

    @Override
    public Object queryByCno(String cno) {
        return consumptionMapper.queryByCno(cno);
    }

}
