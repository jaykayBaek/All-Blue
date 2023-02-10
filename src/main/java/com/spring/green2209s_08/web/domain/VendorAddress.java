package com.spring.green2209s_08.web.domain;

import javax.persistence.Embeddable;

@Embeddable
public class VendorAddress {
    private String postcode;
    private String street;
    private String detail;
}
