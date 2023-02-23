package com.spring.green2209s_08.web.controller.vendor.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class LicenseRequest {
    private String storeName;
    private String licenseNo;
    private String zipcode;
    private String address;
    private String detail;
}
