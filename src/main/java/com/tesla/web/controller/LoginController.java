package com.tesla.web.controller;

import com.tesla.domain.Employee;
import com.tesla.domain.IpLogin;
import com.tesla.qo.JsonResult;
import com.tesla.service.IEmployeeService;
import com.tesla.service.IIpLoginService;
import com.tesla.service.IPermissionService;
import com.tesla.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IIpLoginService iIpLoginService;

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password, HttpServletRequest request){


        Employee employee = employeeService.login(username,password);
        //存在 往session 存入对象
        if (employee !=null) {
//            HttpSession session = UserContext.getSession();
//            session.setAttribute("EMPLOYEE_IN_SESSION",employee);
            //往session存入员工对象
            UserContext.setEmployee(employee);
            if (!employee.isAdmin()) { //不是管理员
                List<String> expressions = permissionService.queryExpressionsByEmployeeId(employee.getId());
//                session.setAttribute("EXPRESSIONS_IN_SESSION",expressions);
                UserContext.setExpressions(expressions);
            }


            //登录成功  插入ip数据
            String ip = request.getRemoteAddr();
            iIpLoginService.addIp(username,ip);

            return new JsonResult(true,"success");
        }else {
            return new JsonResult(false,"用户名或密码错误");
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        HttpSession session = UserContext.getSession();
        //清楚对象session 存入的值
        session.invalidate();
        return "redirect:/static/login.html";
    }

    @RequestMapping("/nopermission")
    public String nopermission(){
        return "common/nopermission";
    }
}
