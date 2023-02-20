package com.spring.green2209s_08.web.controller.vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class VendorInfoChangeRequest {
    private String vendorName;
    private String vendorPhoneNo;
    private String vendorEmail;
}
