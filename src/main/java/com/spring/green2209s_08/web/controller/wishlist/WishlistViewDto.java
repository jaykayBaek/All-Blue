package com.spring.green2209s_08.web.controller.wishlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WishlistViewDto {
    private Long itemId;
    private String savedImageName;
    private String itemName;
    private int salePrice;
    private int deliveryPrice;
    private int stockQuantity;
    private int selectCount;
    private Long vendorLicenseId;
    private String storeName;

    public void assignCount(Integer selectCount){
        this.selectCount = selectCount;
    }
}
