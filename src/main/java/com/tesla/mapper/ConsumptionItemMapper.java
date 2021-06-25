package com.tesla.mapper;

import com.tesla.domain.ConsumptionItem;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface ConsumptionItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConsumptionItem record);

    ConsumptionItem selectByPrimaryKey(Long id);

    List<ConsumptionItem> selectAll();

    int updateByPrimaryKey(ConsumptionItem record);

    List<ConsumptionItem> selectForList(QueryObject qo);

    List<ConsumptionItem> selectByCno(String cno);
}