package com.spring.green2209s_08.web.controller.admin;

import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import com.spring.green2209s_08.web.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminApiController {

    private final ItemService itemService;
    @PatchMapping("/item/status")
    public ResponseEntity<StatusResponse> changeStatus(
            @RequestParam Long itemId, @RequestParam ItemStatus itemStatus){
        itemService.changeStatus(itemId, itemStatus);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "상품 정보 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }
}
