package com.spring.green2209s_08.web.controller.item.api;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.controller.vendor.VendorHomeResponse;
import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.repository.ItemRepository;
import com.spring.green2209s_08.web.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemApiController {

    private final ItemService itemService;

    @PatchMapping("/name")
    public ResponseEntity<StatusResponse> changeItemName(@RequestParam Long itemId, @RequestParam String itemName, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

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
    public ResponseEntity<StatusResponse> changeNumber(@ModelAttribute EditNumberRequest editNumberRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

        /* --- 수정하고자 하는 상품에 대한 유효성 검사(본인이 게재한 상품이 맞는지, 존재하는 상품인지) --- */
        itemService.validateVendorIdToItem(vendorId, editNumberRequest.getItemId());

        itemService.changeNumber(editNumberRequest);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "금액 및 수량 변경완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PatchMapping("/fish")
    public ResponseEntity<StatusResponse> changeFish(@ModelAttribute EditFishRequest editFishRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

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
    public ResponseEntity<StatusResponse> changeProduct(@ModelAttribute EditProductRequest editProductRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

        /* --- 수정하고자 하는 상품에 대한 유효성 검사(본인이 게재한 상품이 맞는지, 존재하는 상품인지) --- */
        itemService.validateVendorIdToItem(vendorId, editProductRequest.getItemId());

        itemService.changeProduct(editProductRequest);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "상품 정보 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }


}
