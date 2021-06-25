package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.IpLogin;
import com.tesla.mapper.IpLoginMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IIpLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IpLoginServiceImpl implements IIpLoginService {
    @Autowired
    private IpLoginMapper ipLoginMapper;

    @Override
    public void save(IpLogin ipLogin) {
        ipLoginMapper.insert(ipLogin);
    }

    @Override
    public void update(IpLogin ipLogin) {
        ipLoginMapper.updateByPrimaryKey(ipLogin);
    }

    @Override
    public void delete(Long id) {
        ipLoginMapper.deleteByPrimaryKey(id);
    }

    @Override
    public IpLogin get(Long id) {
        return ipLoginMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<IpLogin> listAll() {
        return ipLoginMapper.selectAll();
    }

    @Override
    public PageInfo<IpLogin> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<IpLogin> ipLogins = ipLoginMapper.selectForList(qo);
        return new PageInfo(ipLogins);
       }

    @Override
    public void addIp(String username, String ip) {
        //创建一个对象把数据封装进去传入数据库
        IpLogin ipLogin = new IpLogin();
        ipLogin.setUsername(username);
        ipLogin.setIp(ip);
        ipLogin.setTime(new Date());
        ipLoginMapper.insert(ipLogin);
    }

    @Override
    public IpLogin tip(String username) {
        return ipLoginMapper.tip(username);
    }
}
