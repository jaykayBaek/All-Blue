package com.spring.green2209s_08.web.controller.item.api;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import com.spring.green2209s_08.web.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemApiController {

    private final ItemService itemService;

    @PatchMapping("/name")
    public ResponseEntity<StatusResponse> changeItemName(@RequestParam Long itemId, @RequestParam String itemName,
                                                         @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){
        /* --- 수정하고자 하는 상품에 대한 유효성 검사(본인이 게재한 상품이 맞는지, 존재하는 상품인지) --- */
        itemService.validateVendorIdToItem(vendorId, itemId);

        itemService.changeItemName(itemId, itemName);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "노출상품명 변경완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }
    @PatchMapping("/number")
    public ResponseEntity<StatusResponse> changeNumber(@ModelAttribute EditNumberRequest request,
                                                       @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){

        /* --- 수정하고자 하는 상품에 대한 유효성 검사(본인이 게재한 상품이 맞는지, 존재하는 상품인지) --- */
        itemService.validateVendorIdToItem(vendorId, request.getItemId());

        itemService.changeNumber(request);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "금액 및 수량 변경완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PatchMapping("/fish")
    public ResponseEntity<StatusResponse> changeFish(@ModelAttribute EditFishRequest editFishRequest,
                                                     @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){

        /* --- 수정하고자 하는 상품에 대한 유효성 검사(본인이 게재한 상품이 맞는지, 존재하는 상품인지) --- */
        itemService.validateVendorIdToItem(vendorId, editFishRequest.getItemId());

        itemService.changeFish(editFishRequest);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "물고기 정보 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PatchMapping("/product")
    public ResponseEntity<StatusResponse> changeProduct(@ModelAttribute EditProductRequest editProductRequest,
                                                        @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){

        /* --- 수정하고자 하는 상품에 대한 유효성 검사(본인이 게재한 상품이 맞는지, 존재하는 상품인지) --- */
        itemService.validateVendorIdToItem(vendorId, editProductRequest.getItemId());

        itemService.changeProduct(editProductRequest);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "상품 정보 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PatchMapping("/thumbnail")
    public ResponseEntity<StatusResponse> changeThumbnail(@RequestParam MultipartFile thumbnail, @RequestParam Long itemId,
                                                          HttpServletRequest request) throws IOException {
//        FileUpload fileUpload = new FileUpload();
//        ItemImage itemImage = fileUpload.thumbnailImageUpload(thumbnail);


        return null;
    }

    @PatchMapping("/extra")
    public ResponseEntity<StatusResponse> changeExtra(@RequestParam List<MultipartFile> images,
                                                      @RequestParam Long itemId,
                                                      HttpServletRequest request) throws IOException {
        log.info("itemId = {}", itemId);
        log.info("images = {}", images);
//        FileUpload fileUpload = new FileUpload();
//        ItemImage itemImage = fileUpload.thumbnailImageUpload(thumbnail);

        return null;
    }

    @PatchMapping("/status")
    public ResponseEntity<StatusResponse> changeStatus(@RequestParam Long itemId, @RequestParam String itemStatus,
                                                       @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId) {
        log.info("vendor={}, {}, {}", vendorId, itemId, itemStatus);

        ItemStatus status = Enum.valueOf(ItemStatus.class, itemStatus);
        itemService.validateVendorIdToItem(vendorId, itemId);
        itemService.changeStatus(itemId, status);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "상품 정보 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

}
