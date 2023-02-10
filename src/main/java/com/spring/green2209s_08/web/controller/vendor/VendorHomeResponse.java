package com.spring.green2209s_08.web.controller.vendor;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VendorHomeResponse {
    private String vendorLoginId;
    private String vendorName;
    private String vendorEmail;
    private String vendorPhoneNo;
}
