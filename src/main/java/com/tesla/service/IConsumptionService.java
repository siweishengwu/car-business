package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Consumption;
import com.tesla.domain.ConsumptionItem;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IConsumptionService {
    void save(Consumption consumption);
    void update(Consumption consumption);
    void delete(Long id);
    Consumption get(Long id);
    List<Consumption> listAll();

    PageInfo<Consumption> query(QueryObject qo);

    Consumption save(Long appointmentId);

    Object queryByCno(String cno);
}
