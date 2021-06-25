package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Role;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IRoleService {
    void save(Role role, Long[] permissionIds);
    void update(Role role, Long[] permissionIds);
    void delete(Long id);
    Role get(Long id);
    List<Role> listAll();

    PageInfo<Role> query(QueryObject qo);

    List<Role> queryByEmployeeId(Long employeeId);
}
