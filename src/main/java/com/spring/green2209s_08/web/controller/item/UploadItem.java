package com.spring.green2209s_08.web.controller.item;

import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UploadItem {
    private Long id;
    private String itemName;
    private ItemStatus itemStatus;
    private int price;
    private int stockQuantity;
    private LocalDate uploadDate;
    private String categoryName;
    private String categoryId;
}
