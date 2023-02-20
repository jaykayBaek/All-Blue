package com.spring.green2209s_08.web.controller.vendor.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VendorInfoChangeStatusResponse {
    private final String CODE;
    private final String MESSAGE;
    private final String SUCCESS;
    private final VendorInfoChangeResponse response;
}
