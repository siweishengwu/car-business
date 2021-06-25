package com.tesla.mapper;

import com.tesla.domain.Department;
import com.tesla.domain.Employee;
import com.tesla.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    List<Employee> selectForList(QueryObject qo);

    void insertRelation(@Param("employeeId") Long employeeId, @Param("roleId") Long roleId);

    void deleteRelation(Long employeeId);

    Boolean selectByUsername(String username);

    Employee selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    void changePassword(Employee employee);
}