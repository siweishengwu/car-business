package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Department;
import com.tesla.mapper.DepartmentMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void save(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageInfo<Department> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Department> departments = departmentMapper.selectForList(qo);
        return new PageInfo(departments);
       }
}
