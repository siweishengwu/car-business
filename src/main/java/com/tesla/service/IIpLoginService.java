package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.IpLogin;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IIpLoginService {
    void save(IpLogin ipLogin);
    void update(IpLogin ipLogin);
    void delete(Long id);
    IpLogin get(Long id);
    List<IpLogin> listAll();

    PageInfo<IpLogin> query(QueryObject qo);

    void addIp(String username, String ip);

    IpLogin tip(String username);
}
