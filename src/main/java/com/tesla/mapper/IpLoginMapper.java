package com.tesla.mapper;

import com.tesla.domain.IpLogin;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IpLoginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IpLogin record);

    IpLogin selectByPrimaryKey(Long id);

    List<IpLogin> selectAll();

    int updateByPrimaryKey(IpLogin record);

    List<IpLogin> selectForList(QueryObject qo);

    IpLogin tip(String username);
}