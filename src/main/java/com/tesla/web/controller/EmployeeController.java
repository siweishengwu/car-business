package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Department;
import com.tesla.domain.Employee;
import com.tesla.domain.IpLogin;
import com.tesla.domain.Role;
import com.tesla.qo.EmployeeQueryObject;
import com.tesla.qo.JsonResult;
import com.tesla.service.IDepartmentService;
import com.tesla.service.IEmployeeService;
import com.tesla.service.IIpLoginService;
import com.tesla.service.IRoleService;
import com.tesla.util.RequiredPermission;
import com.tesla.util.UserContext;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IIpLoginService iIpLoginService;

    //处理查询所有部门请求
    @RequestMapping("/list")
    @RequiredPermission(name = "员工查询", expression = "employee:list")
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        //回显员工信息和查询数据对象
        PageInfo<Employee> pageInfo = employeeService.query(qo);
        //查询ip提示
        String username = UserContext.getEmployee().getUsername();
        IpLogin ipLogin = iIpLoginService.tip(username);
        model.addAttribute("ipLogin",ipLogin);
        //回显部门信息下拉框
        List<Department> departments = departmentService.listAll();
        model.addAttribute("departments", departments);
        model.addAttribute("pageInfo", pageInfo);
        return "employee/list";
    }

    //处理input新增/修改
    @RequestMapping("/input")
    @RequiredPermission(name = "员工input", expression = "employee:input")
    public String input(Model model, Long id) {
        //查询所有部门
        List<Department> departments = departmentService.listAll();
        model.addAttribute("departments", departments);
        //查询所有角色
        List<Role> roles = roleService.listAll();
        model.addAttribute("roles", roles);
        if (id != null) {//去修改
            Employee employee = employeeService.get(id);
            model.addAttribute("employee", employee);

            //查询此员工所具有的角色
            List<Role> myRoles = roleService.queryByEmployeeId(employee.getId());
            model.addAttribute("myRoles", myRoles);
        }
        return "employee/input";
    }

    //删除
    @RequestMapping("/delete")
    @RequiredPermission(name = "员工删除", expression = "employee:delete")
    public String delete(Long id) {
        if (id != null) {
            employeeService.delete(id);
        }
        return "redirect:/employee/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "员工新增或者修改", expression = "employee:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Employee employee, Long[] roleIds) {
//        int i = 1/0;
        try {
            if (employee.getId() != null) {
                employeeService.update(employee, roleIds);
            } else {
                employeeService.save(employee, roleIds);
            }
            return new JsonResult(true, "编辑成功");
        } catch (Exception e) {
            return new JsonResult(false, e.getMessage());
        }
    }

    @RequestMapping("/checkUserName")
    @RequiredPermission(name = "检查用户名", expression = "employee:checkUserName")
    @ResponseBody
    public Map<String, Boolean> checkUserName(String username) {
        Map<String, Boolean> data = new HashMap<>();
        data.put("valid", employeeService.checkUserName(username));
        return data;
    }

    //处理导出员工请求
    @RequestMapping("/exportXls")
    public void exportXls(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=employee.xls");
        Workbook workbook = employeeService.exportXls();
        //获取响应对象的输出流,这样才可以把响应给用户下载文件回浏览器
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/importXls")
    public String importXls(MultipartFile file) throws Exception{
        System.out.println(file.getName());
        System.out.println(file.getSize());
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        employeeService.importXls(wb);
        return "redirect:/employee/list";
    }

    // 跳转到修改页面
    @RequestMapping("/changePasswordInput")
    public String changePasswordInput(){
        return "/employee/changePasswordInput";
    }

    //修改密码响应json记得贴注解
    @RequestMapping("/changePassword")
    @ResponseBody
    public JsonResult changePassword(String oldPassword,String newPassword){
        try {
            employeeService.changePassword(oldPassword,newPassword);
            return new JsonResult(true,"修改成功");
        } catch (Exception e) {
            return new JsonResult(false,"修改失败");
        }
    }
}
