package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Consumption;
import com.tesla.domain.ConsumptionItem;
import com.tesla.mapper.ConsumptionItemMapper;
import com.tesla.mapper.ConsumptionMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IConsumptionItemService;
import com.tesla.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumptionItemServiceImpl implements IConsumptionItemService {
    @Autowired
    private ConsumptionItemMapper consumptionItemMapper;
    @Autowired
    private ConsumptionMapper consumptionMapper;

    @Override
    public void save(ConsumptionItem consumptionItem) {
        //除了往结算明细保存数据以外
        consumptionItem.setCreateTime(new Date());
        consumptionItem.setCreateUserId(UserContext.getEmployee().getId());
        consumptionItemMapper.insert(consumptionItem);
        //还要找对应的结算单修改那些 汇总的 金额
        Consumption consumption = consumptionMapper.selectByCno(consumptionItem.getCno());

        consumption.setTotalAmount(consumption.getTotalAmount().add(consumptionItem.getAmount()));
        consumption.setDiscountAmount(consumption.getDiscountAmount().add(consumptionItem.getDiscountAmount()));
        consumption.setPayAmount(consumption.getPayAmount().add(consumptionItem.getPayAmount()));

        // 修改汇总
        consumptionMapper.updateSum(consumption);
    }

    @Override
    public void update(ConsumptionItem consumptionItem) {
        consumptionItemMapper.updateByPrimaryKey(consumptionItem);
    }

    @Override
    public void delete(Long id) {
        consumptionItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ConsumptionItem get(Long id) {
        return consumptionItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ConsumptionItem> listAll() {
        return consumptionItemMapper.selectAll();
    }

    @Override
    public PageInfo<ConsumptionItem> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<ConsumptionItem> consumptionItems = consumptionItemMapper.selectForList(qo);
        return new PageInfo(consumptionItems);
       }

    @Override
    public List<ConsumptionItem> queryByCno(String cno) {
        return consumptionItemMapper.selectByCno(cno);
    }

    @Override
    public void deleteItems(Long[] itemIds) {
        for (Long itemId : itemIds) {
            if (itemId != null && itemId >= 0) {
                ConsumptionItem consumptionItem = consumptionItemMapper.selectByPrimaryKey(itemId);
                Consumption consumption = consumptionMapper.selectByCno(consumptionItem.getCno());

                consumption.setTotalAmount(consumption.getTotalAmount().subtract(consumptionItem.getAmount()));
                consumption.setDiscountAmount(consumption.getDiscountAmount().subtract(consumptionItem.getDiscountAmount()));
                consumption.setPayAmount(consumption.getPayAmount().subtract(consumptionItem.getPayAmount()));
                consumptionMapper.updateSum(consumption);
                consumptionItemMapper.deleteByPrimaryKey(itemId);
            }
        }
    }

}
