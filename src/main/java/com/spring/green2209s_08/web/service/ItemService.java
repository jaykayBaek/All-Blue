package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.item.api.EditFishRequest;
import com.spring.green2209s_08.web.controller.item.api.EditNumberRequest;
import com.spring.green2209s_08.web.controller.item.api.EditProductRequest;
import com.spring.green2209s_08.web.controller.search.FishRequestDto;
import com.spring.green2209s_08.web.controller.search.ItemRequestDto;
import com.spring.green2209s_08.web.controller.search.ProductRequestDto;
import com.spring.green2209s_08.web.domain.Fish;
import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.domain.Product;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.PageNotFoundException;
import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.SearchErrorResult;
import com.spring.green2209s_08.web.repository.CategoryRepository;
import com.spring.green2209s_08.web.repository.ItemImageRepository;
import com.spring.green2209s_08.web.repository.ItemRepository;
import com.spring.green2209s_08.web.repository.VendorRepository;
import com.spring.green2209s_08.web.repository.vendor.viewRepository.VendorViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final VendorViewRepository vendorViewRepository;


    @Transactional
    public Item addItem(Item item) {
        Item savedItem = itemRepository.save(item);

        List<ItemImage> itemImages = savedItem.getItemImages();
        for (ItemImage itemImage : itemImages) {
            itemImage.saveItemInfo(savedItem);
        }
        itemImageRepository.saveAll(itemImages);
        return savedItem;
    }

    public Page<Item> findUploadItemList(Long vendorId, Pageable pageable) {
        return vendorViewRepository.findUploadItemList(vendorId, pageable);
    }

    public void validateVendorIdToItem(Long vendorId, Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);

        if(findItem.isEmpty()){
            throw new ItemException(ItemErrorResult.ITEM_NOT_FOUND);
        }

        Long findVendorId = findItem.get().getVendor().getId();

        if(!findVendorId.equals(vendorId)){
            throw new ItemException(ItemErrorResult.ITEM_EDIT_FAIL_VENDOR_NOT_MATCH);
        }
    }

    public Fish findUploadFishByItemId(Long itemId) {
        return (Fish) itemRepository.findById(itemId).get();
    }

    public Product findUploadProductByItemId(Long itemId) {
        return (Product) itemRepository.findById(itemId).get();

    }

    @Transactional
    public void changeItemName(Long itemId, String itemName) {
        Item item = itemRepository.findById(itemId).get();
        item.changeItemName(itemName);
    }

    @Transactional
    public void changeNumber(EditNumberRequest request) {
        Item item = itemRepository.findById(request.getItemId()).get();
        item.changeNumber(request.getPrice(), request.getSalePrice(), request.getStockQuantity(), request.getDeliveryPrice());
    }

    @Transactional
    public void changeFish(EditFishRequest request) {
        Fish fish = (Fish) itemRepository.findById(request.getItemId()).get();
        fish.changeFish(request.getBreederName(), request.getFishSex(), request.getSize());
    }

    @Transactional
    public void changeProduct(EditProductRequest request) {
        Product product = (Product) itemRepository.findById(request.getItemId()).get();
        product.changeProduct(request.getBrandName());
    }


    public Item findItem(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);

        if(findItem.isEmpty()){
            throw new PageNotFoundException(SearchErrorResult.ITEM_NOT_FOUND);
        }
        return findItem.get();
    }
}
