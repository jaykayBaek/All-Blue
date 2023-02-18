package com.spring.green2209s_08.web.controller.item.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditProductRequest {
    private Long itemId;
    private String brandName;
}
