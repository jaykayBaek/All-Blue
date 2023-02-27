package com.spring.green2209s_08.web.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {
    READY("상품 준비중"),
    ON_DELIVERY("배송중"),
    COMPLETE("배송 완료");

    String description;
}
