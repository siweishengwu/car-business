package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Employee;
import com.tesla.mapper.EmployeeMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IEmployeeService;
import com.tesla.util.UserContext;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public void save(Employee employee, Long[] roleIds) {
        //除了往员工表插入数据以外,还要往employee_role插入关系数据
        employeeMapper.insert(employee);
        if (roleIds !=null && roleIds.length>0) {
            for (Long roleId : roleIds) {
                employeeMapper.insertRelation(employee.getId(),roleId);
            }
        }
    }

    @Override
    public void update(Employee employee, Long[] roleIds) {
        //除了删除员工表中的数据
        employeeMapper.updateByPrimaryKey(employee);
        //删除employee_role中此员工的关系数据
        employeeMapper.deleteRelation(employee.getId());
        //插入新的员工新角色数据
        if(roleIds != null && roleIds.length>0){
            for (Long roleId : roleIds) {
                employeeMapper.insertRelation(employee.getId(),roleId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public PageInfo<Employee> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo(employeeMapper.selectForList(qo));
    }

    @Override
    public Boolean checkUserName(String username) {
        Boolean employee = employeeMapper.selectByUsername(username);
        return employee == null;
        /*
        if(employee == null){
            return true;
        }else {
            return false;
        }
         */
    }

    @Override
    public Employee login(String username, String password) {
        return employeeMapper.selectByUsernameAndPassword(username,password);
    }

    @Override
    public Workbook exportXls() {
        // 创建excel文件,在内存中
        Workbook wb = new HSSFWorkbook();
        //创建sheet
        Sheet sheet = wb.createSheet("员工名单");
        //标题行
        Row row = sheet.createRow(0);
        //设置内容到单元格中
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("邮箱");
        row.createCell(2).setCellValue("年龄");
        //查询员工数据
        List<Employee> employees = employeeMapper.selectAll();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            //创建行(每个员工就是一行)
            row = sheet.createRow(i + 1);
            //设置内容到单元格中
            row.createCell(0).setCellValue(employee.getName());
            row.createCell(1).setCellValue(employee.getEmail());
            row.createCell(2).setCellValue(employee.getAge());
        }
        return wb;
    }

    @Override
    public void importXls(Workbook wb) {
        //读取第一张表
        Sheet sheet = wb.getSheetAt(0);
        //获取最后一行的索引
        int lastRowNum = sheet.getLastRowNum();
        //从索引1的行数开始读(忽略标题行(0))
        for (int i = 1; i <= lastRowNum; i++) {
            Employee employee = new Employee();
            //获取行数据封装到对象的对应属性上
            Row row = sheet.getRow(i);
            employee.setName(row.getCell(0).getStringCellValue());
            employee.setUsername(row.getCell(1).getStringCellValue());
            employee.setEmail(row.getCell(2).getStringCellValue());
            employee.setAge((int)row.getCell(3).getNumericCellValue());
            employee.setPassword("1");
            employeeMapper.insert(employee);
        }
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Employee employee = employeeMapper.selectByPrimaryKey(UserContext.getEmployee().getId());
        if (oldPassword.equals(employee.getPassword())) {
            employee.setPassword(newPassword);
            employeeMapper.changePassword(employee);
        }else {
            throw new RuntimeException("修改失败");
        }
    }
}
