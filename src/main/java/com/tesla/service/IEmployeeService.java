package com.tesla.service;


import com.github.pagehelper.PageInfo;
import com.tesla.domain.Employee;
import com.tesla.qo.QueryObject;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface IEmployeeService {
    void save(Employee employeee, Long[] roleIds);
    void update(Employee employeee, Long[] roleIds);
    void delete(Long id);
    Employee get(Long id);
    List<Employee> listAll();

    PageInfo<Employee> query(QueryObject qo);

    Boolean checkUserName(String username);

    Employee login(String username, String password);

    Workbook exportXls();

    void importXls(Workbook wb);

    void changePassword(String oldPassword, String newPassword);
}
