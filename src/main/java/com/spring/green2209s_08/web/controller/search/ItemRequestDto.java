package com.spring.green2209s_08.web.controller.search;

import com.spring.green2209s_08.web.domain.Category;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.domain.Review;
import com.spring.green2209s_08.web.domain.Vendor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemRequestDto {
    private Long id;
    private String itemName;
    private Category category;

    private int price;
    private int salePrice;
    private int stockQuantity;
    private int totalPrice;
    private int deliveryPrice;
    private String content;
    private List<ItemImage> itemImages = new ArrayList<>();
    private Vendor vendor;
    private LocalDate uploadDate;
    private List<Review> reviews = new ArrayList<>();

    private String type;



    public ItemRequestDto(Long id, String itemName, Category category, int price, int salePrice, int stockQuantity, int deliveryPrice, String content, List<ItemImage> itemImages, Vendor vendor, LocalDate uploadDate, List<Review> reviews) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.deliveryPrice = deliveryPrice;
        this.content = content;
        this.itemImages = itemImages;
        this.vendor = vendor;
        this.uploadDate = uploadDate;
        this.reviews = reviews;

        this.totalPrice = salePrice + deliveryPrice;
    }

    public void assignType(String type){
        this.type = type;
    }
}
