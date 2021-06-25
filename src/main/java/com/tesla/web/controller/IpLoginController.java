package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.IpLogin;
import com.tesla.qo.IpLoginQueryObject;
import com.tesla.service.IIpLoginService;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ipLogin")
public class IpLoginController {

    @Autowired
    private IIpLoginService ipLoginService;

    //处理查询所有部门请求
    @RequestMapping("/list")
    @RequiredPermission(name = "部门查询",expression = "ipLogin:list")
    public String list(Model model, @ModelAttribute("qo")IpLoginQueryObject qo){
        PageInfo<IpLogin> pageInfo = ipLoginService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "ipLogin/list";
    }



}
