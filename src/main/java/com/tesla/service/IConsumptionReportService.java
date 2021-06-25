package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.qo.ConsumptionReportQueryObject;
import com.tesla.vo.ConsumptionReportVO;

import java.util.List;

public interface IConsumptionReportService {
    PageInfo<ConsumptionReportVO> list(ConsumptionReportQueryObject qo);

}
