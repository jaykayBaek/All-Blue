package com.spring.green2209s_08.web.controller.search;

import com.spring.green2209s_08.web.controller.vendor.VendorLicenseDto;
import com.spring.green2209s_08.web.domain.Fish;
import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.Product;
import com.spring.green2209s_08.web.domain.VendorLicense;
import com.spring.green2209s_08.web.repository.VendorLicenseRepository;
import com.spring.green2209s_08.web.service.ItemSearchService;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.service.VendorLicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final ItemSearchService itemSearchService;
    private final ItemService itemService;
    private final VendorLicenseService vendorLicenseService;

    @GetMapping
    public String view(@ModelAttribute ItemSearchCond condition, Pageable pageable, Model model){
        Page<ItemDto> items = itemSearchService.findItemsByCond(condition, pageable);

        model.addAttribute("items", items);
        model.addAttribute("condition", condition);
        return "main/search/search";
    }

    @GetMapping("/detail/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model){
        Item item = itemService.findItem(itemId);
        VendorLicenseDto findVendorLicenseDto = vendorLicenseService.findByItemId(itemId);


        if(item instanceof Fish){
            FishRequestDto request = new FishRequestDto(
                    item.getId(), item.getItemName(), item.getCategory(), item.getPrice(), item.getSalePrice(), item.getStockQuantity(),
                    item.getDeliveryPrice(), item.getContent(), item.getItemImages(), item.getVendor(), item.getUploadDate(),
                    item.getReviews(), ((Fish) item).getBreederName(), ((Fish) item).getSex().getDescription(), ((Fish) item).getSize()
            );
            request.assignType("fish");
            model.addAttribute("item", request);
        }else if(item instanceof Product){
            ProductRequestDto request = new ProductRequestDto(
                    item.getId(), item.getItemName(), item.getCategory(), item.getPrice(), item.getSalePrice(), item.getStockQuantity(),
                    item.getDeliveryPrice(), item.getContent(), item.getItemImages(), item.getVendor(), item.getUploadDate(),
                    item.getReviews(), ((Product) item).getBrandName()
            );
            request.assignType("product");
            model.addAttribute("item", request);
        }



        model.addAttribute("itemId", itemId);
        model.addAttribute("vendorLicense", findVendorLicenseDto);
        return "main/search/detail";
    }




}
