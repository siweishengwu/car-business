package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.MessageReply;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IMessageReplyService {
    void save(MessageReply messageReply);
    void update(MessageReply messageReply);
    void delete(Long id);
    MessageReply get(Long id);
    List<MessageReply> listAll();

    PageInfo<MessageReply> query(QueryObject qo);

    List<MessageReply> queryListById(Long id);

}
