package com.spring.green2209s_08.web.controller.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutItemDto {
    private Long itemId;
    private String itemName;
    private int itemSelectedQuantity;
}
