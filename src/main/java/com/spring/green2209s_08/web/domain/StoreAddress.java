package com.spring.green2209s_08.web.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StoreAddress {
    private String zipcode;
    private String address;
    private String detail;
}
