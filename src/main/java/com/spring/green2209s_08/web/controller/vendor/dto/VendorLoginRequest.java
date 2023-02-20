package com.spring.green2209s_08.web.controller.vendor.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class VendorLoginRequest {
    @NotBlank
    private String vendorLoginId;
    @NotBlank
    private String vendorPassword;

    public void changeEncodedPassword(String password) {
        this.vendorPassword = password;
    }

}

