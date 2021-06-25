package com.tesla.mapper;

import com.tesla.qo.ConsumptionReportQueryObject;
import com.tesla.vo.ConsumptionReportVO;

import java.util.List;

public interface ConsumptionReportMapper {
    List<ConsumptionReportVO> selectConsumptionReportVO(ConsumptionReportQueryObject qo);
}
