package com.tesla.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConsumptionReportVO {
    private String groupType;
    private int totalCount;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscountAmount;
    private BigDecimal totalPayAmount;
}
