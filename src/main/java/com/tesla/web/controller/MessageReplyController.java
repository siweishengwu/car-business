package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.MessageReply;
import com.tesla.qo.QueryObject;
import com.tesla.service.IMessageReplyService;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messageReply")
public class MessageReplyController {

    @Autowired
    private IMessageReplyService messageReplyService;


    //处理查询所有信息回复请求
    @RequestMapping("/list")
    public String list(Model model, QueryObject qo){
        PageInfo<MessageReply> pageInfo = messageReplyService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "messageReply/list";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(Long id){
        if (id != null) {
            messageReplyService.delete(id);
        }
        return "redirect:/messageReply/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(MessageReply messageReply){
        if (messageReply.getId() != null) {
            messageReplyService.update(messageReply);
        }else {
            messageReplyService.save(messageReply);
        }
        return "redirect:/messageReply/list";
    }

}
