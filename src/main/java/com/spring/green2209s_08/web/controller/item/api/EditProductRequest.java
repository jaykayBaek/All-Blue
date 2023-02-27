package com.spring.green2209s_08.web.controller.item.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class EditProductRequest {
    private Long itemId;
    private String brandName;
}
