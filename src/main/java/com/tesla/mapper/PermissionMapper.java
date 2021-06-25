package com.tesla.mapper;

import com.tesla.domain.Permission;
import com.tesla.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission permission);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission permission);

    List<Permission> selectForList(QueryObject qo);

    void batchInsert(@Param("permissions") Set<Permission> permissions);

    List<Permission> selectByRoleId(Long roleId);

    List<String> selectExpressionsByEmployeeId(Long employeeId);
}
