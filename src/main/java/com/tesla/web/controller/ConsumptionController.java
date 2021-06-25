package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Consumption;
import com.tesla.domain.ConsumptionItem;
import com.tesla.domain.SystemDictionary;
import com.tesla.enums.ConsumptionStatusEnum;
import com.tesla.qo.ConsumptionQueryObject;
import com.tesla.qo.QueryObject;
import com.tesla.service.IBusinessService;
import com.tesla.service.IConsumptionItemService;
import com.tesla.service.IConsumptionService;
import com.tesla.service.ISystemDictionaryService;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/consumption")
public class ConsumptionController {

    @Autowired
    private IConsumptionService consumptionService;
    @Autowired
    private IBusinessService businessService;
    @Autowired
    private IConsumptionItemService consumptionItemService;
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequestMapping("save")
    @RequiredPermission(name = "根据预约生成结算单",expression = "appointment:save")
    public String save(Long appointmentId){
        Consumption consumption =  consumptionService.save(appointmentId);
        //为了让员工操作起来方便
        return "redirect:/consumption/input?id="+ consumption.getId();
    }



    //处理查询所有消费请求
    @RequestMapping("/list")
    @RequiredPermission(name = "消费查询",expression = "consumption:list")
    public String list(Model model, @ModelAttribute("qo")ConsumptionQueryObject qo){

        //把所有门店查询出来存到Model中
        model.addAttribute("businesses",businessService.listAll());
        //把所有结算单状态存到Model中
        model.addAttribute("consumptionStatusEnums", ConsumptionStatusEnum.values());

        PageInfo<Consumption> pageInfo = consumptionService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "consumption/list";
    }

    //input跳转
    @RequestMapping("/input")
    public String input(Long id,Model model){
        model.addAttribute("businesses",businessService.listAll());


        //查询业务大类存到model
        model.addAttribute("categories",systemDictionaryService.queryBySn("business"));
        //查询支付方式存到model
        model.addAttribute("payTypes",systemDictionaryService.queryBySn("pay_type"));


        if (id != null) {
            Consumption consumption = consumptionService.get(id);
            model.addAttribute("consumption", consumption);

            //根据结算单的流水号查询这个结算单明细
            List<ConsumptionItem> consumptionItems = consumptionItemService.queryByCno(consumption.getCno());
            model.addAttribute("consumptionItems", consumptionItems);




//            BigDecimal totalAmount = BigDecimal.ZERO;
//            BigDecimal totalDiscountAmount = BigDecimal.ZERO;
//
//            for (ConsumptionItem consumptionItem : consumptionItems) {
//                BigDecimal amount = consumptionItem.getAmount();
//                totalAmount = totalAmount.add(amount);
//
//                BigDecimal discountAmount = consumptionItem.getDiscountAmount();
//                totalDiscountAmount = totalDiscountAmount.add(discountAmount);
//            }
//
//            model.addAttribute("totalAmount",totalAmount);
//            model.addAttribute("totalDiscountAmount",totalDiscountAmount);

        }
        return "consumption/input";
    }

    //删除
    @RequestMapping("/delete")
    @RequiredPermission(name = "消费删除",expression = "consumption:delete")
    public String delete(Long id){
        if (id != null) {
            consumptionService.delete(id);
        }
        return "redirect:/consumption/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "消费新增或删除",expression = "consumption:saveOrUpdate")
    public String saveOrUpdate(Consumption consumption){
        if (consumption.getId() != null) {
            consumptionService.update(consumption);
        }else {
            consumptionService.save(consumption);
        }
        return "redirect:/consumption/list";
    }

}
