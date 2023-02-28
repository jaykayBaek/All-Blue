package com.spring.green2209s_08.web.controller.vandorManagement;

import com.spring.green2209s_08.web.domain.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ManageDeliveryDto {
    private Long ordersId;
    private String impUid;
    private String itemName;
    private Long selectedQuantity;
    private String recipient;
    private String address;
    private String zipcode;
    private String phoneNo;
    private LocalDateTime createdDate;
    private DeliveryStatus deliveryStatus;
}