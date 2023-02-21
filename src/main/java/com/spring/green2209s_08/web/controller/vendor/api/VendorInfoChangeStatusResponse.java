package com.spring.green2209s_08.web.controller.vendor.api;

import com.spring.green2209s_08.web.controller.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
public class VendorInfoChangeStatusResponse extends StatusResponse {
    private final VendorInfoChangeResponse response;

    public VendorInfoChangeStatusResponse(String CODE, String MESSAGE, String SUCCESS, VendorInfoChangeResponse response) {
        super(CODE, MESSAGE, SUCCESS);
        this.response = response;
    }
}
