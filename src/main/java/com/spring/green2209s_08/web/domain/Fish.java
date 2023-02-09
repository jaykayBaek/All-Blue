package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.domain.enums.FishSex;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fish extends Item {
    private String breederName;
    private FishSex sex;
    private String size;
    private void createFish(String name, int price, int deliveryPrice, int stockQuantity, String breederName, FishSex sex, String size) {
        super.createItem(name, price, deliveryPrice, stockQuantity);
        this.breederName = breederName;
        this.sex = sex;
        this.size = size;
    }
    public static Fish getFish(String breederName, FishSex sex, String size, String name, int price, int deliveryPrice, int stockQuantity){
        Fish fish = new Fish();
        fish.createFish(name, price, deliveryPrice, stockQuantity, breederName, sex, size);
        return fish;
    }
}
