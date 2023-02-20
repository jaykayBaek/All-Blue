package com.spring.green2209s_08.web.controller.myhome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private Long id;
    private Long memberId;

    private String recipient;
    private String phoneNo;
    private String zipcode;
    private String address;
    private String detail;
}
