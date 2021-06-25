package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Business;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IBusinessService {
    void save(Business business);
    void update(Business business);
    void delete(Long id);
    Business get(Long id);
    List<Business> listAll();

    PageInfo<Business> query(QueryObject qo);

    Business queryMainBusiness();

}
