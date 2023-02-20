package com.spring.green2209s_08.web.controller.myhome;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressRequest {
    private String recipient;
    private String phoneNo;
    private String zipcode;
    private String address;
    private String detail;
}
