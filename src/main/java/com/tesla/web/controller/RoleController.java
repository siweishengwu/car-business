package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Permission;
import com.tesla.domain.Role;
import com.tesla.qo.QueryObject;
import com.tesla.service.IPermissionService;
import com.tesla.service.IRoleService;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService iPermissionService;
    
    //处理查询所有部门请求
    @RequestMapping("/list")
    @RequiredPermission(name = "角色查询",expression = "role:list")
    public String list(Model model, QueryObject qo){
        PageInfo<Role> pageInfo = roleService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "role/list";
    }

    //  处理新增修改请求
    @RequestMapping("/input")
    @RequiredPermission(name = "角色新增修改",expression = "role:input")
    public String input(Model model,Long id){
        if (id != null){//修改
            //回显此角色 和 权限
            Role role = roleService.get(id);
            model.addAttribute("role",role);

            //根据角色id查询这个角色的权限,并存到model中,以便在页面回显
            List<Permission> myPermissions =  iPermissionService.queryByRoleId(role.getId());
            model.addAttribute("myPermissions",myPermissions);
        }
        List<Permission> permissions = iPermissionService.listAll();
        model.addAttribute("permissions",permissions);
        return "role/input";
    }

    //删除
    @RequestMapping("/delete")
    @RequiredPermission(name = "角色删除",expression = "role:delete")
    public String delete(Long id){

        if (id != null) {
            roleService.delete(id);
        }
        return "redirect:/role/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "角色新增或删除",expression = "role:saveOrUpdate")
    public String saveOrUpdate(Role role,Long[] permissionIds){
        if (role.getId() != null) {
            roleService.update(role,permissionIds);
        }else {
            roleService.save(role,permissionIds);
        }
        return "redirect:/role/list";
    }

}
