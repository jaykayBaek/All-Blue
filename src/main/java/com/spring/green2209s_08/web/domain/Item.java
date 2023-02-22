package com.spring.green2209s_08.web.domain;

import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import com.spring.green2209s_08.web.exception.errorResult.VendorErrorResult;
import com.spring.green2209s_08.web.exception.VendorException;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String itemName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private int price;
    private int salePrice;
    private int stockQuantity;
    private int deliveryPrice;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    // 이미지는 최대 8장(8장)까지 저장할 수 있다
    @BatchSize(size = 8)
    @OneToMany(mappedBy = "item")
    private List<ItemImage> itemImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private LocalDate uploadDate;

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    /**
     * 생성
     */
    protected void saveImage(List<ItemImage> itemImages) {
        this.itemImages = itemImages;
    }
    protected void createItem(String itemName, int price, int deliveryPrice, int stockQuantity, String content, LocalDate uploadDate) {
        this.itemName = itemName;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.stockQuantity = stockQuantity;
        this.content = content;
        this.uploadDate = uploadDate;
    }

    public void updateItemStatus(ItemStatus itemStatus){
        this.itemStatus = itemStatus;
    }

    public void assignVendor(Vendor vendor){
        this.vendor = vendor;
    }

    /**
     * 변경
     */
    public void changeItemName(String itemName){
        this.itemName = itemName;
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

    public void assignCategory(Category category) {
        this.category = category;
    }

    public void changeNumber(Integer price, Integer salePrice, Integer stockQuantity, Integer deliveryPrice) {
        this.price = price;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.deliveryPrice = deliveryPrice;
    }
}
