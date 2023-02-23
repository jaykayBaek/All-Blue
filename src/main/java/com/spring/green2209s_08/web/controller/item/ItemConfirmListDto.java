package com.spring.green2209s_08.web.controller.item;

import com.spring.green2209s_08.web.domain.Category;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemConfirmListDto {
    private Long id;
    private Category category;
    private String itemName;
    private int salePrice;
    private LocalDate uploadDate;
    private ItemStatus itemStatus;
}
