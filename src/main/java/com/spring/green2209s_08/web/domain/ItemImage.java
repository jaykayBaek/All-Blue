package com.spring.green2209s_08.web.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemImage {
    @Id @GeneratedValue
    @Column(name = "item_image_id")
    private Long id;

//  파일 업로드 가능성을 열어두고 필드를 설계
    @ColumnDefault(value = "false")
    private Boolean thumbnailImage;
    private String savedImageName;
    private String originalImageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void saveItemInfo(Item item){
        this.item = item;
    }

    public ItemImage(String savedImageName, String originalImageName, Boolean isThumbnail) {
        this.savedImageName = savedImageName;
        this.originalImageName = originalImageName;
        this.thumbnailImage = isThumbnail;
    }
}