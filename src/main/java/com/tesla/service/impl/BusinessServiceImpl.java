package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Business;
import com.tesla.mapper.BusinessMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements IBusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public void save(Business business) {
        businessMapper.insert(business);
    }

    @Override
    public void update(Business business) {
        businessMapper.updateByPrimaryKey(business);
    }

    @Override
    public void delete(Long id) {
        businessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Business get(Long id) {
        return businessMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Business> listAll() {
        return businessMapper.selectAll();
    }

    @Override
    public PageInfo<Business> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Business> businesss = businessMapper.selectForList(qo);
        return new PageInfo(businesss);
    }

    @Override
    public Business queryMainBusiness() {

        return businessMapper.selectMainBusiness();
    }
}
