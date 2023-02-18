package com.spring.green2209s_08.web.controller.item.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class EditNumberRequest {
    private Long itemId;
    private Integer price;
    private Integer salePrice;
    private Integer stockQuantity;
    private Integer deliveryPrice;

}
