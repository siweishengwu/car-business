package com.tesla.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MessageBoard {
    /** */
    private Long id;

    /** 昵称*/
    private String nickname;

    /** 留言内容*/
    private String content;

    /** 留言时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 业务大类*/
//    private Long categoryId;
    private SystemDictionary category;

    /** 业务小类*/
//    private Long categoryItemId;
    private SystemDictionary categoryItem;

    /** 回复状态(未回复/已回复)*/
    private Boolean replystatus;

}