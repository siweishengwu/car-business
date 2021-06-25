package com.tesla.web.interceptor;

import com.tesla.domain.Employee;
import com.tesla.util.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee employee = UserContext.getEmployee();
        //判断是否登录
        //session不为空为true
//        if (request.getSession().getAttribute("EMPLOYEE_IN_SESSION") != null) {

        if (employee != null) {
            return true;
        }
        //session为空重定向
        response.sendRedirect("/static/login.html");
        return false;
    }
}
