package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.domain.enums.FishSex;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fish extends Item {
    private String breederName;

    @Enumerated(EnumType.STRING)
    private FishSex sex;

    private String size;

    private void createFish(String itemName, int price, int deliveryPrice, int stockQuantity, String breederName, FishSex sex, String size, String content, LocalDate uploadDate) {
        super.createItem(itemName, price, deliveryPrice, stockQuantity, content, uploadDate);
        this.breederName = breederName;
        this.sex = sex;
        this.size = size;
    }

    public static Fish getFish(String breederName, FishSex sex, String size, String itemName, int price, int deliveryPrice, int stockQuantity, String content, LocalDate uploadDate){
        Fish fish = new Fish();
        fish.createFish(itemName, price, deliveryPrice, stockQuantity, breederName, sex, size, content, uploadDate);
        return fish;
    }

    public void saveImage(List<ItemImage> itemImages){
        super.saveImage(itemImages);
    }
}
