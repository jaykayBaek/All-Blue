package com.spring.green2209s_08.web.controller.vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendorLicenseDto {
    private Long vendorLicenseId;
    private String licenseNo;
    private String address;
    private String detail;
    private String zipcode;
    private String storeName;
}
