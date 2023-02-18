package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.domain.enums.FishSex;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Item{
    private String brandName;

    private void createProduct(String brandName, String itemName, int price, int deliveryPrice, int stockQuantity, String content, LocalDate uploadDate) {
        super.createItem(itemName, price, deliveryPrice, stockQuantity, content, uploadDate);
        this.brandName = brandName;

    }
    public static Product getProduct(String brandName, String itemName, int price, int deliveryPrice, int stockQuantity, String content, LocalDate uploadDate){
        Product product = new Product();
        product.createProduct(brandName, itemName, price, deliveryPrice, stockQuantity, content, uploadDate);
        return product;
    }

    public void saveImage(List<ItemImage> itemImages){
        super.saveImage(itemImages);
    }

    public void changeProduct(String brandName) {
        this.brandName = brandName;
    }
}