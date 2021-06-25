package com.tesla.mapper;

import com.tesla.domain.Role;
import com.tesla.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectForList(QueryObject qo);

    void insertRelation(@Param("roleId") Object roleId, @Param("permissionId") Long permissionId);

    void deleteRelation(Long roleId);

    List<Role> selectByEmployeeId(Long employeeId);

}
