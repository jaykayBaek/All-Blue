package com.spring.green2209s_08.web.controller.checkout;

import lombok.Getter;

@Getter
public class AddressOrderResponse {
    private Long id;
    private String recipient;
    private String phoneNo;
    private String zipcode;
    private String address;

    public AddressOrderResponse(Long id, String recipient, String phoneNo, String zipcode, String address, String detail) {
        this.id = id;
        this.recipient = recipient;
        this.phoneNo = phoneNo;
        this.zipcode = zipcode;
        this.address = address + " " + detail;
    }
}
