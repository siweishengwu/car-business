package com.tesla.mapper;

import com.tesla.domain.MessageReply;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface MessageReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageReply record);

    MessageReply selectByPrimaryKey(Long id);

    List<MessageReply> selectAll();

    int updateByPrimaryKey(MessageReply record);

    List<MessageReply> selectForList(QueryObject qo);

    List<MessageReply> selectAllById(Long id);

}