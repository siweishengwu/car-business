package com.tesla.mapper;

import com.tesla.domain.Department;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department department);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department department);

    List<Department> selectForList(QueryObject qo);
}