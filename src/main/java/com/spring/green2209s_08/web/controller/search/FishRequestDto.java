package com.spring.green2209s_08.web.controller.search;

import com.spring.green2209s_08.web.domain.Category;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.domain.Review;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.domain.enums.FishSex;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class FishRequestDto extends ItemRequestDto {
    private String breederName;
    private FishSex fishSex;
    private String size;

    public FishRequestDto(Long id, String itemName, Category category, int price, int salePrice, int stockQuantity, int deliveryPrice, String content, List<ItemImage> itemImages, Vendor vendor, LocalDate uploadDate, List<Review> reviews, String breederName, FishSex fishSex, String size) {
        super(id, itemName, category, price, salePrice, stockQuantity, deliveryPrice, content, itemImages, vendor, uploadDate, reviews);
        this.breederName = breederName;
        this.fishSex = fishSex;
        this.size = size;
    }
}
