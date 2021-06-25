package com.tesla.mapper;

import com.tesla.domain.MessageBoard;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface MessageBoardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageBoard record);

    MessageBoard selectByPrimaryKey(Long id);

    List<MessageBoard> selectAll();

    int updateByPrimaryKey(MessageBoard record);

    List<MessageBoard> selectForList(QueryObject qo);
}