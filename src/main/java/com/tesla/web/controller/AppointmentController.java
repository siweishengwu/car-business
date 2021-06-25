package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Appointment;
import com.tesla.domain.Business;
import com.tesla.domain.SystemDictionary;
import com.tesla.enums.AppointmentStatusEnum;
import com.tesla.qo.AppointmentQueryObject;
import com.tesla.service.IAppointmentService;
import com.tesla.service.IBusinessService;
import com.tesla.service.ISystemDictionaryService;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private IBusinessService businessService;
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    //处理查询所有部门请求
    @RequestMapping("/list")
    @RequiredPermission(name = "部门查询",expression = "appointment:list")
    public String list(Model model, @ModelAttribute("qo") AppointmentQueryObject qo){
        //查询所有预约业务的子数据字典
        List<Business> businesses = businessService.listAll();
        model.addAttribute("businesses",businesses);
        //传入business到数据查询业务
        List<SystemDictionary> systemDictionaries = systemDictionaryService.queryBySn("business");
        model.addAttribute("systemDictionaries",systemDictionaries);

        //把预约的状态值存到model中
        model.addAttribute("AppointmentStatusEnums", AppointmentStatusEnum.values());

        PageInfo<Appointment> pageInfo = appointmentService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "appointment/list";
    }

    //删除
    @RequestMapping("/delete")
    @RequiredPermission(name = "部门删除",expression = "appointment:delete")
    public String delete(Long id){
        if (id != null) {
            appointmentService.delete(id);
        }
        return "redirect:/appointment/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "部门新增或删除",expression = "appointment:saveOrUpdate")
    public String saveOrUpdate(Appointment appointment){
        if (appointment.getId() != null) {
            appointmentService.update(appointment);
        }else {
            //因为业务要求从 系统内部预约需要设置 1(履行中) 进去
            appointment.setStatus(AppointmentStatusEnum.PERFORM.getStatus());
//            appointmentService.save(appointment, ip);
        }
        return "redirect:/appointment/list";
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Appointment appointment){
        appointmentService.updateStatus(appointment);
        return "redirect:/appointment/list";
    }

}
