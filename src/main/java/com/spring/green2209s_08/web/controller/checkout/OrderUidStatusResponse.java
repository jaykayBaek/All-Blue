package com.spring.green2209s_08.web.controller.checkout;

import com.spring.green2209s_08.web.controller.StatusResponse;
import lombok.Getter;

@Getter
public class OrderUidStatusResponse extends StatusResponse {
    private String OrderUid;

    public OrderUidStatusResponse(String CODE, String MESSAGE, String SUCCESS, String orderUid) {
        super(CODE, MESSAGE, SUCCESS);
        OrderUid = orderUid;
    }
}
