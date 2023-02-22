package com.spring.green2209s_08.web.controller.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {
    private Long id;
    private String itemName;
    private int salePrice;
    private String savedImageName;
    private Long reviewCount;
    private Double likeAvg;
}
