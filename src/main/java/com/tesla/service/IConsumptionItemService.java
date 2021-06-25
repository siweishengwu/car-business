package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.ConsumptionItem;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IConsumptionItemService {
    void save(ConsumptionItem consumptionItem);
    void update(ConsumptionItem consumptionItem);
    void delete(Long id);
    ConsumptionItem get(Long id);
    List<ConsumptionItem> listAll();

    PageInfo<ConsumptionItem> query(QueryObject qo);

    List<ConsumptionItem> queryByCno(String cno);

    void deleteItems(Long[] itemIds);
}
