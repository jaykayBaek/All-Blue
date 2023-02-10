package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.exception.errorResult.VendorErrorResult;
import com.spring.green2209s_08.web.exception.VendorException;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int deliveryPrice;
    private int stockQuantity;

    // 이미지는 최대 8장(8장)까지 저장할 수 있다
    @BatchSize(size = 8)
    @OneToMany(mappedBy = "item")
    private List<ItemImage> itemImages = new ArrayList<>();

    @Embedded
    private Category category;

    protected void createItem(String name, int price, int deliveryPrice, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.stockQuantity = stockQuantity;
    }

    /**
     * 비즈니스 로직
     */
    /* --- 재고 증가 --- */
    public void addStock(int quantity){
        if(quantity < 0){
            throw new VendorException(VendorErrorResult.ADD_NEGATIVE_STOCK_QUANTITY);
        }
        this.stockQuantity += quantity;
    }

    /* --- 재고 감소 --- */
    public void subtractStock(int quantity){
        if(this.stockQuantity - quantity < 0){
            throw new VendorException(VendorErrorResult.NOT_ENOUGH_STOCK);
        }
        this.stockQuantity -= quantity;
    }
}
