package com.spring.green2209s_08.web.controller.item;

import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorUploadItemResponse {
    private Long id;
    private String itemName;
    private int price;
    private int stockQuantity;
    private LocalDate uploadDate;
    private String categoryName;
    private String categoryId;
    private ItemStatus itemStatus;

}
