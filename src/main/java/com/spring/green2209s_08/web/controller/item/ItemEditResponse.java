package com.spring.green2209s_08.web.controller.item;

import com.spring.green2209s_08.web.domain.Category;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.domain.enums.FishSex;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter @Builder
public class ItemEditResponse {
    private Long id;
    private String itemName;
    private Category category;

    private int price;
    private int salePrice;
    private int stockQuantity;
    private int deliveryPrice;

    private String content;

    private String itemStatus;

    private List<ItemImage> itemImages;

    private String breederName;
    private FishSex fishSex;
    private String size;

    private String brandName;

    public void changeItemStatusToDescription(String itemStatus, int a){
        if(ItemStatus.APPROVAL.equals(itemStatus)){
            this.itemStatus = ItemStatus.APPROVAL.getDescription();
        }else if(ItemStatus.DECISION_IN_PROCESS.equals(itemStatus)){
            this.itemStatus = ItemStatus.DECISION_IN_PROCESS.getDescription();
        }else if(ItemStatus.BLOCK_SELLING.equals(itemStatus)){
            this.itemStatus = ItemStatus.BLOCK_SELLING.getDescription();
        }else if(ItemStatus.SHUT_DOWN.equals(itemStatus)){
            this.itemStatus = ItemStatus.SHUT_DOWN.getDescription();
        }else{
            this.itemStatus = ItemStatus.REJECT_APPROVAL.getDescription();
        }
    }

    public void changeItemStatusToDescription(ItemStatus status){
        this.itemStatus = status.getDescription();
    }

}
