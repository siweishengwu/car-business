package com.tesla.qo;

import lombok.Data;

@Data
public class IpLoginQueryObject extends QueryObject {
    private String username;
    private String ip;
}
