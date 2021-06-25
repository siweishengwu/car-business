package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Role;
import com.tesla.mapper.RoleMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void save(Role role, Long[] permissionIds) {
        //除了往角色表中保存数据
        roleMapper.insert(role);
        //还要往中间表role_permission保存数据
        if (permissionIds !=null && permissionIds.length > 0) {
            for (Long permissionId : permissionIds) {
                roleMapper.insertRelation(role.getId(),permissionId);
            }
        }
    }

    @Override
    public void update(Role role, Long[] permissionIds) {
        //  除了修改角色表中的数据
        roleMapper.updateByPrimaryKey(role);
        // 先从中间表删除与该角色有关数据
        roleMapper.deleteRelation(role.getId());
        //再往中间插入这个角色新分配权限数据
        if (permissionIds !=null && permissionIds.length > 0) {
            for (Long permissionId : permissionIds) {
                roleMapper.insertRelation(role.getId(),permissionId);
            }
        }

    }

    @Override
    public void delete(Long id) {
        //加上判断的代码
        // 先从中间表删除与该角色有关数据
        roleMapper.deleteRelation(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    @Override
    public PageInfo<Role> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Role> roles = roleMapper.selectForList(qo);
        return new PageInfo(roles);
       }

    @Override
    public List<Role> queryByEmployeeId(Long employeeId) {
        return roleMapper.selectByEmployeeId(employeeId);

    }
}
