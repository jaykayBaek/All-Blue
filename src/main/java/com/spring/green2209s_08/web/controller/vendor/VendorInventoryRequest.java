package com.spring.green2209s_08.web.controller.vendor;

import com.spring.green2209s_08.web.domain.enums.FishSex;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VendorInventoryRequest {
    private String itemName;

    private String parentCategory;
    private String childCategory;
    private String grandchildCategory;

    private Integer price;
    private Integer salePrice;
    private Integer stockQuantity;
    private Integer deliveryPrice;
    private String content;

    private MultipartFile thumbnail;
    private List<MultipartFile> extra;

    private String brandName;

    private String breederName;
    private FishSex fishSex;
    private String size;
}
