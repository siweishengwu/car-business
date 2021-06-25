package com.tesla.qo;

import lombok.Data;

@Data
public class QueryObject {
    //当前页
    private int currentPage = 1;
    //每页条数
    private int pageSize = 5;
}
