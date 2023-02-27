package com.spring.green2209s_08.web.controller.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponse {
    private Long ordersId;
    private LocalDateTime createdDate;
    private String orderItemName;
    private int totalPrice;
    private int totalDeliveryPrice;
}
