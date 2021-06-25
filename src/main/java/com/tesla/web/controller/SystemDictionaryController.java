package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.SystemDictionary;
import com.tesla.qo.QueryObject;
import com.tesla.service.ISystemDictionaryService;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemDictionary")
public class SystemDictionaryController {

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    //处理查询所有字典请求
    @RequestMapping("/list")
    @RequiredPermission(name = "字典查询",expression = "systemDictionary:list")
    public String list(Model model, QueryObject qo){
        List<SystemDictionary> systemDictionaries =  systemDictionaryService.queryTree();
        model.addAttribute("systemDictionaries",systemDictionaries);
        return "systemDictionary/list";
    }

    //删除
    @RequestMapping("/delete")
    @RequiredPermission(name = "字典删除",expression = "systemDictionary:delete")
    public String delete(Long id){
        if (id != null) {
            systemDictionaryService.delete(id);
        }
        return "redirect:/systemDictionary/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "字典新增或删除",expression = "systemDictionary:saveOrUpdate")
    public String saveOrUpdate(SystemDictionary systemDictionary){
        if (systemDictionary.getId() != null) {
            systemDictionaryService.update(systemDictionary);
        }else {
            systemDictionaryService.save(systemDictionary);
        }
        return "redirect:/systemDictionary/list";
    }

    @ResponseBody
    @RequestMapping("/queryItemById")
    public List<SystemDictionary> queryItemById(Long id){
        return systemDictionaryService.queryItemById(id);
    }
}
