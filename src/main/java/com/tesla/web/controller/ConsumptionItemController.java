package com.tesla.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tesla.domain.ConsumptionItem;
import com.tesla.domain.SystemDictionary;
import com.tesla.qo.JsonResult;
import com.tesla.qo.QueryObject;
import com.tesla.service.IConsumptionItemService;
import com.tesla.service.IConsumptionService;
import com.tesla.service.ISystemDictionaryService;
import com.tesla.util.RequiredPermission;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/consumptionItem")
public class ConsumptionItemController {

    @Autowired
    private IConsumptionItemService consumptionItemService;
    @Autowired
    private IConsumptionService consumptionService;

    //处理查询所有结算单明细请求
    @RequestMapping("/list")
    @RequiredPermission(name = "结算单明细查询",expression = "consumptionItem:list")
    public String list(Model model, QueryObject qo){
        PageInfo<ConsumptionItem> pageInfo = consumptionItemService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "consumptionItem/list";
    }

    //删除
    @RequestMapping("/delete")
    @RequiredPermission(name = "结算单明细删除",expression = "consumptionItem:delete")
    public String delete(Long id){
        if (id != null) {
            consumptionItemService.delete(id);
        }
        return "redirect:/consumptionItem/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "结算单明细新增或删除",expression = "consumptionItem:saveOrUpdate")
    public String saveOrUpdate(ConsumptionItem consumptionItem){
        if (consumptionItem.getId() != null) {
            consumptionItemService.update(consumptionItem);
        }else {
            consumptionItemService.save(consumptionItem);
        }

        return "redirect:/consumptionItem/list";
    }

    @RequestMapping("/ajaxSave")
    @ResponseBody
    public Map<String,Object> ajaxSave(ConsumptionItem consumptionItem){
        System.out.println("consumptionItem = " + consumptionItem);
        consumptionItemService.save(consumptionItem);
        Map<String,Object> map = new HashMap<>();
        map.put("consumptionItem",consumptionItem);
        map.put("consumption",consumptionService.queryByCno(consumptionItem.getCno()));
        return map;
    }

    @ResponseBody
    @RequestMapping("/deleteIds")
    public JsonResult deleteIds(HttpServletRequest request,ConsumptionItem consumptionItem){
        try {
            Gson gson = new Gson();
            String ids = request.getParameter("ids");
            Long[] itemIds = gson.fromJson(ids, Long[].class);
            consumptionItemService.deleteItems(itemIds);
            return new JsonResult(true,"成功");
        } catch (Exception e) {
            return new JsonResult(false,"失败");
        }
    }
}
