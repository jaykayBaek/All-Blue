package com.spring.green2209s_08.web.controller.vandorManagement;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/management")
public class VendorManagementApiController {
    private final OrderService orderService;

    @PatchMapping("/delivery")
    public ResponseEntity<StatusResponse> deliveryManagement(@SessionAttribute(name = SessionConst.VENDOR_ID) Long vendorId,
                                                             @RequestParam String impUid){
        orderService.setDeliveryStatusOnDelivery(impUid, vendorId);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "배송상태 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

}
