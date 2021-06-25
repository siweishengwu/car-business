package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Permission;
import com.tesla.mapper.PermissionMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void save(Set<Permission> permissions) {
        permissionMapper.batchInsert(permissions);
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Permission get(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public PageInfo<Permission> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Permission> permissions = permissionMapper.selectForList(qo);
        return new PageInfo(permissions);
       }

    @Override
    public List<Permission> queryByRoleId(Long roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }

    @Override
    public List<String> queryExpressionsByEmployeeId(Long employeeId) {
        return permissionMapper.selectExpressionsByEmployeeId(employeeId);
    }
}
