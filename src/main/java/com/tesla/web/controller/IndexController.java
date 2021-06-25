package com.tesla.web.controller;

import com.tesla.domain.Appointment;
import com.tesla.domain.Business;
import com.tesla.domain.SystemDictionary;
import com.tesla.mapper.AppointmentMapper;
import com.tesla.qo.JsonResult;
import com.tesla.service.IAppointmentService;
import com.tesla.service.IBusinessService;
import com.tesla.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private IBusinessService businessService;
    @Autowired
    private ISystemDictionaryService systemDictionaryService;
    @Autowired
    private IAppointmentService appointmentService;

    @RequestMapping("/index")
    public String index(Model model){
        Business business = businessService.queryMainBusiness();
        model.addAttribute("business",business);

        //查询业务这个数据的数据字典
        List<SystemDictionary> systemDictionaries =  systemDictionaryService.queryBySn("business");
        model.addAttribute("systemDictionaries",systemDictionaries);

        //查询所有门店
        List<Business> businesses = businessService.listAll();
        model.addAttribute("businesses",businesses);


        return "/index";
    }

    @ResponseBody
    @RequestMapping("/index/appointment")
    public JsonResult save(Appointment appointment, HttpServletRequest request){
        //登录成功  插入ip数据
        String ip = request.getRemoteAddr();
        appointmentService.save(appointment,ip);
        return new JsonResult(true,"预约成功");
    }
}
