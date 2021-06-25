package com.tesla.util;

import com.tesla.domain.Employee;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.Expression;
import java.rmi.server.ExportException;
import java.util.List;

public abstract class UserContext {

    public static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
    public static final String EXPRESSIONS_IN_SESSION = "EXPRESSIONS_IN_SESSION";

    //获取请求对象
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        return requestAttributes.getRequest();
    }

    //获取session对象
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    //往session存登录员工对象
    public static void setEmployee(Employee employee){
        getSession().setAttribute(EMPLOYEE_IN_SESSION,employee);
    }
    //从session中取员工对象
    public static Employee getEmployee(){
        return ((Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION));
    }

    //往session中存入权限表达式
    public static void setExpressions(List<String> expressions) {
        getSession().setAttribute(EXPRESSIONS_IN_SESSION,expressions);
    }
    //从session中取权限表达式
    public static List<String> getExpressions() {
        return ((List<String>) getSession().getAttribute(EXPRESSIONS_IN_SESSION));
    }
}
