package com.spring.green2209s_08.web.controller.search;

import com.spring.green2209s_08.web.domain.Category;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.domain.Review;
import com.spring.green2209s_08.web.domain.Vendor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ProductRequestDto extends ItemRequestDto {
    private String brandName;

    public ProductRequestDto(Long id, String itemName, Category category, int price, int salePrice, int stockQuantity, int deliveryPrice, String content, List<ItemImage> itemImages, Vendor vendor, LocalDate uploadDate, List<Review> reviews, String brandName) {
        super(id, itemName, category, price, salePrice, stockQuantity, deliveryPrice, content, itemImages, vendor, uploadDate, reviews);
        this.brandName = brandName;
    }
}
