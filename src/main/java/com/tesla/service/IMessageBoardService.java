package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.MessageBoard;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface IMessageBoardService {
    void save(MessageBoard messageBoard);
    void update(MessageBoard messageBoard);
    void delete(Long id);
    MessageBoard get(Long id);
    List<MessageBoard> listAll();

    PageInfo<MessageBoard> query(QueryObject qo);
}
