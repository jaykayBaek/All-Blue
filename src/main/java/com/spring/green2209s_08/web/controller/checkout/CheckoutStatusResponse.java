package com.spring.green2209s_08.web.controller.checkout;

import com.spring.green2209s_08.web.controller.StatusResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class CheckoutStatusResponse extends StatusResponse {
    private final List<ItemOrderResponse> itemOrderResponse;
    private final CheckoutMemberDto memberOrderResponse;
    private final AddressOrderResponse addressOrderResponse;
    private final CheckoutTotalPriceDto checkoutTotalPriceDto;
    public CheckoutStatusResponse(String CODE, String MESSAGE, String SUCCESS, List<ItemOrderResponse> itemOrderResponse, CheckoutMemberDto memberOrderResponse, AddressOrderResponse addressOrderResponse, CheckoutTotalPriceDto checkoutTotalPriceDto) {
        super(CODE, MESSAGE, SUCCESS);
        this.itemOrderResponse = itemOrderResponse;
        this.memberOrderResponse = memberOrderResponse;
        this.addressOrderResponse = addressOrderResponse;
        this.checkoutTotalPriceDto = checkoutTotalPriceDto;
    }
}
