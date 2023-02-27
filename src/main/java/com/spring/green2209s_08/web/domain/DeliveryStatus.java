package com.spring.green2209s_08.web.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {
    PAYMENT_OK("주문 및 결제 완료"),
    READY("상품 준비중"),
    ON_DELIVERY("배송중"),
    COMPLETE("배송 완료");

    String description;
}
