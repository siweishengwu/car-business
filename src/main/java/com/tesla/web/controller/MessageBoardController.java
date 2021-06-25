package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.MessageBoard;
import com.tesla.domain.MessageReply;
import com.tesla.qo.QueryObject;
import com.tesla.service.IMessageBoardService;
import com.tesla.service.IMessageReplyService;
import com.tesla.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/messageBoard")
public class MessageBoardController {

    @Autowired
    private IMessageBoardService messageBoardService;
    @Autowired
    private IMessageReplyService iMessageReplyService;
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    //处理查询所有信息回复请求
    @RequestMapping("/list")
    public String list(Model model, QueryObject qo){
        PageInfo<MessageBoard> pageInfo = messageBoardService.query(qo);

        //查询业务大类存到model
        model.addAttribute("categories",systemDictionaryService.queryBySn("business"));
        model.addAttribute("pageInfo",pageInfo);
        return "messageBoard/list";
    }

    //跳转详情页
    @RequestMapping("/detailPage")
    public String detailPage(Long id,Model model){
        MessageBoard messageBoard = messageBoardService.get(id);
        model.addAttribute("messageBoard",messageBoard);
        List<MessageReply> messageReplyList =  iMessageReplyService.queryListById(id);
        model.addAttribute("count",messageReplyList.size());
        model.addAttribute("messageReplyList",messageReplyList);
        return "messageBoard/detail";
    }





    //删除
    @RequestMapping("/delete")
    public String delete(Long id){
        if (id != null) {
            messageBoardService.delete(id);
        }
        return "redirect:/messageBoard/list";
    }


    //新增
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(MessageBoard messageBoard){
        if (messageBoard.getId() != null) {
            messageBoardService.update(messageBoard);
        }else {
            messageBoardService.save(messageBoard);
        }
        return "redirect:/messageBoard/list";
    }

}
