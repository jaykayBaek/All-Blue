package com.spring.green2209s_08.web.controller.vendor.api;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VendorInfoChangeResponse {
    private Long vendorId;
    private String vendorLoginId;
    private String vendorName;
    private String vendorEmail;
    private String vendorPhoneNo;
}
