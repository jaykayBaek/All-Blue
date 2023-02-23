package com.spring.green2209s_08.web.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendorHomeItemCountResponse {
    private Long uploadItemsCount;
    private Long approvalItemsCount;
}
