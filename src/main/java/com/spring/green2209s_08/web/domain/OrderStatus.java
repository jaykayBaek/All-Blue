package com.spring.green2209s_08.web.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PAYMENT_OK("주문 및 결제 완료"),
    CANCEL("주문취소"),
    RETURN("반품"),
    EXCHANGE("교환"),
    REFUND("환불");

    String description;

}
