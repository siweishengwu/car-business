package com.tesla.service;

import com.tesla.domain.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest {

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void save() {
        Department department = new Department();
        department.setName("公关部");
        department.setSn("DC");
        departmentService.save(department);
    }

    @Test
    public void testData(){
        System.out.println(dataSource);
    }
}