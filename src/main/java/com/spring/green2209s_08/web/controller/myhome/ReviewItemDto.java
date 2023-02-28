package com.spring.green2209s_08.web.controller.myhome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ReviewItemDto {
    private Long ordersId;
    private Long itemId;
    private String savedImageName;
    private String itemName;
    private Long reviewRating;
}
