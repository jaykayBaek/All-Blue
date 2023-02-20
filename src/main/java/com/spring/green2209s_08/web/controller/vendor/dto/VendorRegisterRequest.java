package com.spring.green2209s_08.web.controller.vendor.dto;

import com.spring.green2209s_08.web.domain.enums.AccountType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VendorRegisterRequest {
    private String vendorLoginId;
    private String vendorPassword;
    private String vendorName;
    private String vendorEmail;
    private String vendorPhoneNo;
    public void changeEncodedPassword(String vendorPassword) {
        this.vendorPassword = vendorPassword;
    }
}
