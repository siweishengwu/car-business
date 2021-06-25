package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Department;
import com.tesla.qo.JsonResult;
import com.tesla.qo.QueryObject;
import com.tesla.service.IDepartmentService;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    //处理查询所有部门请求
    @RequestMapping("/list")
    @RequiredPermission(name = "部门查询",expression = "department:list")
    public String list(Model model, QueryObject qo){
        PageInfo<Department> pageInfo = departmentService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "department/list";
    }

    //删除
    @RequestMapping("/delete")
    @RequiredPermission(name = "部门删除",expression = "department:delete")
    public String delete(Long id){
        if (id != null) {
            departmentService.delete(id);
        }
        return "redirect:/department/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "部门新增或删除",expression = "department:saveOrUpdate")
    public String saveOrUpdate(Department department){
        if (department.getId() != null) {
            departmentService.update(department);
        }else {
            departmentService.save(department);
        }
        return "redirect:/department/list";
    }

}
