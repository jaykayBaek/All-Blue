package com.spring.green2209s_08.web.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemImage {
    @Id @GeneratedValue
    @Column(name = "image_file_id")
    private Long id;

//  파일 업로드 가능성을 열어두고 필드를 설계
    private String storedFileName;
    private String uploadFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void saveItemInfo(Item item){
        this.item = item;
    }

    public ItemImage(String storedFileName, String uploadFileName) {
        this.storedFileName = storedFileName;
        this.uploadFileName = uploadFileName;
    }
}