package com.tesla.domain;

import lombok.Data;

@Data
public class Permission {
    private Long id;
    private String name;
    private String expression;
}