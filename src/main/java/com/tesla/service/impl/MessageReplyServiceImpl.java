package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.MessageReply;
import com.tesla.mapper.MessageReplyMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IMessageReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageReplyServiceImpl implements IMessageReplyService {
    @Autowired
    private MessageReplyMapper messageReplyMapper;

    @Override
    public void save(MessageReply messageReply) {
        messageReplyMapper.insert(messageReply);
    }

    @Override
    public void update(MessageReply messageReply) {
        messageReplyMapper.updateByPrimaryKey(messageReply);
    }

    @Override
    public void delete(Long id) {
        messageReplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public MessageReply get(Long id) {
        return messageReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MessageReply> listAll() {
        return messageReplyMapper.selectAll();
    }

    @Override
    public PageInfo<MessageReply> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<MessageReply> messageReplys = messageReplyMapper.selectForList(qo);
        return new PageInfo(messageReplys);
       }

    @Override
    public List<MessageReply> queryListById(Long id) {
        return messageReplyMapper.selectAllById(id);
    }

}
