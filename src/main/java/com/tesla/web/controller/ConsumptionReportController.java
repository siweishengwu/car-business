package com.tesla.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.Consumption;
import com.tesla.domain.ConsumptionItem;
import com.tesla.enums.ConsumptionStatusEnum;
import com.tesla.qo.ConsumptionQueryObject;
import com.tesla.qo.ConsumptionReportQueryObject;
import com.tesla.service.*;
import com.tesla.util.RequiredPermission;
import com.tesla.vo.ConsumptionReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/consumptionReport")
public class ConsumptionReportController {

    @Autowired
    private IConsumptionReportService consumptionReportService;

    @Autowired
    private IBusinessService businessService;


    //处理查询所有消费请求
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") ConsumptionReportQueryObject qo){
        PageInfo<ConsumptionReportVO> pageInfo = consumptionReportService.list(qo);
        model.addAttribute("pageInfo",pageInfo);


        model.addAttribute("businesses",businessService.listAll());
        return "/consumptionReport/list";
    }

    //处理查询所有消费请求
    @RequestMapping("/listBar")
    public String listBar(Model model, @ModelAttribute("qo") ConsumptionReportQueryObject qo){
        qo.setPageSize(0); //不分页查询

        //准备list存各个列的值
        List<String> groupTypes = new ArrayList<>();
        List<BigDecimal> totalAmounts = new ArrayList<>();
        List<BigDecimal> totalDiscountAmounts = new ArrayList<>();
        List<BigDecimal> totalPayAmounts = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();


        List<ConsumptionReportVO> data = consumptionReportService.list(qo).getList();
        for (ConsumptionReportVO vo : data) {
            groupTypes.add(vo.getGroupType());
            totalAmounts.add(vo.getTotalAmount());
            totalDiscountAmounts.add(vo.getTotalDiscountAmount());
            totalPayAmounts.add(vo.getTotalPayAmount());
            counts.add(vo.getTotalCount());

        }
        //把报表页面需要数据存到model中
        model.addAttribute("groupTypes", JSON.toJSONString(groupTypes));
        model.addAttribute("totalAmounts",JSON.toJSONString(totalAmounts));
        model.addAttribute("totalDiscountAmounts",JSON.toJSONString(totalDiscountAmounts));
        model.addAttribute("totalPayAmounts",JSON.toJSONString(totalPayAmounts));
        model.addAttribute("counts",JSON.toJSONString(counts));
        return "/consumptionReport/listBar";
    }
}
