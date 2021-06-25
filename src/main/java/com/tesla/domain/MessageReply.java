package com.tesla.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MessageReply {
    /** */
    private Long id;

    /** 回复内容*/
    private String content;

    /** 所属留言*/
    private Long messageId;

    /** 回复人*/
//    private Long replyUserId;
    private Employee replyUser;

    /** 回复时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}