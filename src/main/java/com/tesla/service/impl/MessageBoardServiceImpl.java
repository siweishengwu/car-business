package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tesla.domain.MessageBoard;
import com.tesla.mapper.MessageBoardMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.IMessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageBoardServiceImpl implements IMessageBoardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;

    @Override
    public void save(MessageBoard messageBoard) {
        messageBoard.setCreateTime(new Date());
        messageBoard.setReplystatus(false);
        messageBoardMapper.insert(messageBoard);
    }

    @Override
    public void update(MessageBoard messageBoard) {
        messageBoardMapper.updateByPrimaryKey(messageBoard);
    }

    @Override
    public void delete(Long id) {
        messageBoardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public MessageBoard get(Long id) {
        return messageBoardMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MessageBoard> listAll() {
        return messageBoardMapper.selectAll();
    }

    @Override
    public PageInfo<MessageBoard> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<MessageBoard> messageBoards = messageBoardMapper.selectForList(qo);
        return new PageInfo(messageBoards);
       }
}
