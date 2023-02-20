package com.spring.green2209s_08.web.controller.vendor.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VendorHomeResponse {
    private Long vendorId;
    private String vendorLoginId;
    private String vendorName;
    private String vendorEmail;
    private String vendorPhoneNo;
}
