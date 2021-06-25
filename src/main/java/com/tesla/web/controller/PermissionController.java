package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Permission;
import com.tesla.qo.JsonResult;
import com.tesla.qo.QueryObject;
import com.tesla.service.IPermissionService;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.util.*;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private ApplicationContext ctx;

    //处理查询所有权限请求
    @RequestMapping("/list")
    @RequiredPermission(name = "权限查询", expression = "permission:list")
    public String list(Model model, QueryObject qo) {
        PageInfo<Permission> pageInfo = permissionService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "permission/list";
    }

    @RequestMapping("/reload")
    @RequiredPermission(name = "权限加载", expression = "permission:reload")
    @ResponseBody
    public JsonResult reload() {

        try {
            //查询数据库已存在
            List<Permission> allPermissions = permissionService.listAll();
            List<String> expressions = new ArrayList<>();
            for (Permission permission : allPermissions) {
                expressions.add(permission.getExpression());
            }

            Set<Permission> permissions = new LinkedHashSet<>();

            //获取容器对象
            Map<String, Object> beans = ctx.getBeansWithAnnotation(Controller.class);
            //从容器对象获取所有控制器对象,获取其字节对象
            Collection<Object> controllers = beans.values();
            for (Object controller : controllers) {

                Method[] methods = controller.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    RequiredPermission anno = method.getAnnotation(RequiredPermission.class);

                    //创建对象封装数据,并保存
                    Permission permission = new Permission();
                    permission.setName(anno.name());
                    permission.setExpression(anno.expression());

                    //加入判断,若数据库不存在就存到项目
                    if (!expressions.contains(anno.expression())) {
                        permissions.add(permission);
                    }
                }
            }
            //  MyBatis支持批量存储 支持批量存储foreach
            if (permissions.size() > 0) {
                permissionService.save(permissions);
            }
            return new JsonResult(true, "加载成功");
        } catch (Exception e) {
            return new JsonResult(false, "加载失败");
        }
    }
}
