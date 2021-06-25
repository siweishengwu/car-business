package com.tesla.mapper;

import com.tesla.domain.Business;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface BusinessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Business record);

    Business selectByPrimaryKey(Long id);

    List<Business> selectAll();

    int updateByPrimaryKey(Business record);

    List<Business> selectForList(QueryObject qo);

    Business selectMainBusiness();
}