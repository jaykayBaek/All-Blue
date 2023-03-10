package com.spring.green2209s_08.web.controller.order;

import com.spring.green2209s_08.web.domain.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class OrderSearchCond {
    private String query;
    private DeliveryStatus deliveryStatus;
}