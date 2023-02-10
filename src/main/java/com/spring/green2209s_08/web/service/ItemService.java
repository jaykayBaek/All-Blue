package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

//    @Transactional
//    public void addFish(Fish fish, List<ItemImage> images){
//        ItemImage thumbnailItemImage = images.get(0);
//        fish.addThumbnailImage(thumbnailItemImage);
//
//        for (ItemImage image : images) {
//            fish.uploadImage(image);
//        }
//
//        itemRepository.save(fish);
//    }
//
//    @Transactional
//    public void addProduct(Product product, List<ItemImage> images){
//        if(images.size()>8){
//            throw new VendorException(VendorErrorResult.OVER_UPLOAD_IMAGE);
//        }
//
//        for (ItemImage image : images) {
//            product.uploadImage(image);
//        }
//
//        itemRepository.save(product);
//    }

}
