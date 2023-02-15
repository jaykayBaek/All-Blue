package com.spring.green2209s_08.web.controller;

import com.spring.green2209s_08.web.controller.vendor.VendorInventoryRequest;
import com.spring.green2209s_08.web.domain.Fish;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.domain.Product;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.utility.FileUpload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
@Controller
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/enroll")
    public String itemUpload(
            @ModelAttribute @Validated VendorInventoryRequest request, BindingResult bindingResult,
            @RequestParam(value="imageSrcArr[]") List<String> imageSource) throws IOException {

        /* --- 파일 저장시키기(대표이미지, 상세이미지들) --- */
        FileUpload fileUpload = new FileUpload();
        ItemImage savedThumbnailImage = fileUpload.thumbnailImageUpload(request.getThumbnail());
        List<ItemImage> saveImages = fileUpload.extraImagesUpload(request.getExtra());
        saveImages.add(savedThumbnailImage);

        /* --- 저장시킨 파일 이름 디비에 저장시키기 --- */

        String parentCategory = request.getParentCategory();
        if(parentCategory.equals("01")){
            Fish fish = Fish.getFish(
                    request.getBreederName(), request.getFishSex(),
                    request.getSize(), request.getItemName(),
                    request.getPrice(), request.getDeliveryPrice(),
                    request.getStockQuantity(), request.getContent()
            );
            fish.saveImage(saveImages);
            itemService.enrollProduct(fish);
        }
        else if(parentCategory.equals("02")){
            Product product = Product.getProduct(
                    request.getBrandName(), request.getItemName(),
                    request.getPrice(), request.getDeliveryPrice(),
                    request.getStockQuantity(), request.getContent()
            );
            product.saveImage(saveImages);
            itemService.enrollProduct(product);
        }


        return null;
    }
}
