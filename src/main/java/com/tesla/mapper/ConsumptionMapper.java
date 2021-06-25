package com.tesla.mapper;

import com.tesla.domain.Consumption;
import com.tesla.domain.ConsumptionItem;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface ConsumptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Consumption record);

    Consumption selectByPrimaryKey(Long id);

    List<Consumption> selectAll();

    int updateByPrimaryKey(Consumption record);

    List<Consumption> selectForList(QueryObject qo);

    Consumption selectByCno(String cno);

    void updateSum(Consumption consumption);

    Object queryByCno(String cno);
}