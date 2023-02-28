package com.spring.green2209s_08.web.controller.myhome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewItemDto {
    private Long itemId;
    private Long ordersId;
    private String savedImageName;
    private String itemName;
}
