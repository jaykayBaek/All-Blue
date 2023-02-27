package com.spring.green2209s_08.web.controller.checkout;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderResponse {
    private Long itemId;
    private String itemName;
    private int salePrice;
}
