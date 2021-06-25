package com.tesla.qo;

import com.tesla.domain.ConsumptionItem;
import lombok.Data;

@Data
public class JsonResult {
    private boolean success;
    private String msg;

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
