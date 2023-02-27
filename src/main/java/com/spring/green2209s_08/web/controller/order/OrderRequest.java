package com.spring.green2209s_08.web.controller.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class OrderRequest {
    private String impUid;
    private String orderUid;
    private String paymentMethod;
    private String orderItemName;
    private String email;
    private String recipient;
    private String zipcode;
    private String address;
    private String currency;

    private int totalPrice;
    private int totalDeliveryPrice;

    private List<Long> orderItemIdList;
}