package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Department;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IDepartmentService {
    void save(Department department);
    void update(Department department);
    void delete(Long id);
    Department get(Long id);
    List<Department> listAll();

    PageInfo<Department> query(QueryObject qo);
}
