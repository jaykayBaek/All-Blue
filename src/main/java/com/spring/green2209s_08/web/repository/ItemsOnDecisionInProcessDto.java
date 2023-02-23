package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Category;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsOnDecisionInProcessDto {
    private Long itemId;
    private Category category;
    private String itemName;
    private int salePrice;
    private LocalDateTime localDateTime;
    private ItemStatus itemStatus;
}