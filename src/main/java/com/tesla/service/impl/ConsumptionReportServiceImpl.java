package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.mapper.ConsumptionReportMapper;
import com.tesla.qo.ConsumptionReportQueryObject;
import com.tesla.service.IConsumptionReportService;
import com.tesla.vo.ConsumptionReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumptionReportServiceImpl implements IConsumptionReportService {

    @Autowired
    private ConsumptionReportMapper consumptionReportMapper;

    @Override
    public PageInfo<ConsumptionReportVO> list(ConsumptionReportQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        return new PageInfo<>(consumptionReportMapper.selectConsumptionReportVO(qo));
    }
}
