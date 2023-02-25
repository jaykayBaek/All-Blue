package com.spring.green2209s_08.web.controller.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutTotalPriceDto {
    private int totalSalePrice;
    private int totalDeliveryPrice;

}
