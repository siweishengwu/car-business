package com.tesla.qo;

import lombok.Data;

@Data
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private Long deptId;
}
