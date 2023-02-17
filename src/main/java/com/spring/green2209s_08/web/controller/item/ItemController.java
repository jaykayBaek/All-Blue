package com.spring.green2209s_08.web.controller.item;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.vendor.VendorHomeResponse;
import com.spring.green2209s_08.web.controller.vendor.VendorInventoryRequest;
import com.spring.green2209s_08.web.domain.*;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import com.spring.green2209s_08.web.service.CategoryService;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.service.VendorService;
import com.spring.green2209s_08.web.utility.FileUpload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
@Controller
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final VendorService vendorService;

    @PostMapping("/enroll")
    public String itemUpload(
            @ModelAttribute @Validated VendorInventoryRequest inventoryRequest, BindingResult bindingResult, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);
        Vendor findVendor = vendorService.findById(vendorId).get();

        /* --- 이미지 파일 서버에 저장시키기(대표이미지, 상세이미지들) --- */
        FileUpload fileUpload = new FileUpload();

        ItemImage savedThumbnailImage = fileUpload.thumbnailImageUpload(inventoryRequest.getThumbnail());
        List<ItemImage> saveImages = fileUpload.extraImagesUpload(inventoryRequest.getExtra());

        saveImages.add(savedThumbnailImage);

        /* --- content 안의 임시 이미지들 저장 및 (임시)이미지 제거 --- */
        fileUpload.copyImageToChangedPath(inventoryRequest.getContent());

        /* --- DB에 저장하기 전 이미지태그 src 값 변경 --- */
        String changedContent = fileUpload.changeImagePathInContent(inventoryRequest.getContent());

        /* --- 카테고리 찾기 --- */
        String grandchildCategory = inventoryRequest.getGrandchildCategory();
        Category category = categoryService.findCategoryId(grandchildCategory);

        /* --- 저장시킨 파일 이름 디비에 저장시키기 --- */
        String parentCategory = inventoryRequest.getParentCategory();
        if(parentCategory.equals("01")){
            Fish fish = Fish.getFish(
                    inventoryRequest.getBreederName(), inventoryRequest.getFishSex(),
                    inventoryRequest.getSize(), inventoryRequest.getItemName(),
                    inventoryRequest.getPrice(), inventoryRequest.getDeliveryPrice(),
                    inventoryRequest.getStockQuantity(), changedContent, LocalDate.now()
            );
            fish.updateItemStatus(ItemStatus.DECISION_IN_PROCESS);
            fish.saveImage(saveImages);
            fish.assignCategory(category);
            fish.assignVendor(findVendor);
            itemService.enrollProduct(fish);
        }
        else if(parentCategory.equals("02")){
            Product product = Product.getProduct(
                    inventoryRequest.getBrandName(), inventoryRequest.getItemName(),
                    inventoryRequest.getPrice(), inventoryRequest.getDeliveryPrice(),
                    inventoryRequest.getStockQuantity(), changedContent, LocalDate.now()
            );
            product.updateItemStatus(ItemStatus.DECISION_IN_PROCESS);
            product.saveImage(saveImages);
            product.assignCategory(category);
            product.assignVendor(findVendor);
            itemService.enrollProduct(product);
        }

        return "redirect:/item/list";
    }

    @GetMapping("/list")
    public String itemUploadList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            HttpServletRequest request, Model model){
        VendorHomeResponse response = getVendorHomeResponse(request);
        Long vendorId = response.getVendorId();

        Page<Item> itemList = itemService.findUploadItemList(vendorId, pageable);

        Page<UploadItem> items = itemList.map(i -> UploadItem.builder()
                .id(i.getId())
                .itemName(i.getItemName())
                .itemStatus(i.getItemStatus())
                .price(i.getPrice() - i.getSalePrice())
                .stockQuantity(i.getStockQuantity())
                .uploadDate(i.getUploadDate())
                .categoryName(i.getCategory().getGrandchildName())
                .categoryId(i.getCategory().getGrandchildId())
                .build());

        model.addAttribute("vendor", response);
        model.addAttribute("items", items);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", items.hasNext());
        model.addAttribute("hasPrev", items.hasPrevious());

        return "main/item/itemUploadList";
    }

    @GetMapping("/{itemId}/{categoryId}/edit")
    public String itemEditForm
            (@PathVariable Long itemId, @PathVariable String categoryId,
             HttpServletRequest request, Model model){
        VendorHomeResponse response = getVendorHomeResponse(request);
        Long vendorId = response.getVendorId();

        /* --- 수정하고자 하는 상품에 대한 유효성 검사(본인이 게재한 상품이 맞는지, 존재하는 상품인지) --- */
        itemService.validateVendorIdToItem(vendorId, itemId);

        categoryId = categoryId.substring(0, 2);

        /* --- 01 -> 생물 --- */
        if(categoryId.equals("01")){
            Fish fish = itemService.findUploadFishByItemId(itemId);
            ItemEditResponse itemEditResponse = ItemEditResponse.builder()
                    .id(fish.getId())
                    .itemName(fish.getItemName())
                    .category(fish.getCategory())
                    .price(fish.getPrice())
                    .salePrice(fish.getSalePrice())
                    .stockQuantity(fish.getStockQuantity())
                    .deliveryPrice(fish.getDeliveryPrice())
                    .content(fish.getContent())
                    .breederName(fish.getBreederName())
                    .fishSex(fish.getSex())
                    .size(fish.getSize())
                    .build();
            itemEditResponse.changeItemStatusToDescription(fish.getItemStatus());

            /* --- 개제한 이미지들 model에 담음 --- */
            List<String> extraImages = new ArrayList<>();
            for (ItemImage itemImage : fish.getItemImages()) {
                if(itemImage.getThumbnailImage() == true){
                    model.addAttribute("thumbnailImage", itemImage.getSavedImageName());
                }
                else {
                    extraImages.add(itemImage.getSavedImageName());
                }
            }
            model.addAttribute("extraImages", extraImages);
            model.addAttribute("uploadItem", itemEditResponse);
        }
        else if(categoryId.equals("02")){
            Product product = itemService.findUploadProductByItemId(itemId);
            ItemEditResponse itemEditResponse = ItemEditResponse.builder()
                    .id(product.getId())
                    .itemName(product.getItemName())
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .salePrice(product.getSalePrice())
                    .stockQuantity(product.getStockQuantity())
                    .deliveryPrice(product.getDeliveryPrice())
                    .content(product.getContent())
                    .itemImages(product.getItemImages())
                    .breederName(product.getBrandName())
                    .build();
            itemEditResponse.changeItemStatusToDescription(product.getItemStatus());

            List<String> extraImages = new ArrayList<>();
            for (ItemImage itemImage : product.getItemImages()) {
                if(itemImage.getThumbnailImage() == true){
                    model.addAttribute("thumbnailImage", itemImage.getSavedImageName());
                }
                else {
                    extraImages.add(itemImage.getSavedImageName());
                }
            }
            model.addAttribute("extraImages", extraImages);
            model.addAttribute("uploadItem", itemEditResponse);
        }

        model.addAttribute("vendor", response);
        return "main/item/itemEditForm";
    }

    private VendorHomeResponse getVendorHomeResponse(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

        Optional<Vendor> findVendor = vendorService.findById(vendorId);

        Optional<VendorHomeResponse> vendorHomeResponse = findVendor.map(m -> new VendorHomeResponse(
                vendorId, m.getVendorLoginId(), m.getVendorName(), m.getVendorEmail(), m.getVendorPhoneNo()
        ));

        VendorHomeResponse response = vendorHomeResponse.get();
        return response;
    }
}
