package com.tesla.web.interceptor;

import com.tesla.domain.Employee;
import com.tesla.service.IPermissionService;
import com.tesla.util.RequiredPermission;
import com.tesla.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CheckPermissionInterceptor implements HandlerInterceptor {
    //检查权限
    @Autowired
    private IPermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //把图转成代码
        //获取当前访问用户
//        Employee employee = (Employee) request.getSession().getAttribute("EMPLOYEE_IN_SESSION");
        Employee employee = UserContext.getEmployee();
        if (employee.isAdmin()) { //是管理员
            return true;
        }

        //不是管理员
        //HandlerMethod 是SpringMVC 会使用这个类的对象进项目所有控制器方法进行封装
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //获取访问的处理方法上的注解
        RequiredPermission anno = handlerMethod.getMethodAnnotation(RequiredPermission.class);

        if (anno == null){
            return true;
        }

        //这个方法需要权限控制 到这个位置
        //获取当前访问方法权限表达式
        String expression = anno.expression();
        //查询当前访问员工权限
//        List<String> expressions = ((List<String>) request.getSession().getAttribute("EXPRESSIONS_IN_SESSION"));
        List<String> expressions = UserContext.getExpressions();
        if (expressions.contains(expression)) { //访问的员工有此权限
            return  true;
        }
        //不是管理员,方法需要权限,但没有权限
        response.sendRedirect("/nopermission");
        return false;
    }
}
