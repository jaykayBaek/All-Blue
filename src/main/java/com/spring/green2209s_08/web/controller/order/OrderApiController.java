package com.spring.green2209s_08.web.controller.order;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.controller.checkout.ItemOrderResponse;
import com.spring.green2209s_08.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<StatusResponse> order(
            @ModelAttribute OrderRequest orderRequest,
            @SessionAttribute(name= SessionConst.MEMBER_ID) Long memberId){

        orderService.order(memberId, orderRequest);
        
        //삭제와(wishlist장바구니) 생성(주문내역Orders)이 동시에 일어나서 Http 상태코드 200번을 내림
        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "주문 성공", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }
}
