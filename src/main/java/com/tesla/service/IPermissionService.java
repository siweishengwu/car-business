package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Permission;
import com.tesla.qo.QueryObject;

import java.util.List;
import java.util.Set;

public interface IPermissionService {
    void save(Set<Permission> permission);
    void update(Permission permission);
    void delete(Long id);
    Permission get(Long id);
    List<Permission> listAll();

    PageInfo<Permission> query(QueryObject qo);

    List<Permission> queryByRoleId(Long roleId);

    List<String> queryExpressionsByEmployeeId(Long employeeId);

}
