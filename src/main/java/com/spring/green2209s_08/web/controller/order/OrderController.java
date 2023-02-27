package com.spring.green2209s_08.web.controller.order;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.domain.Orders;
import com.spring.green2209s_08.web.service.OrderItemService;
import com.spring.green2209s_08.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @GetMapping("/list")
    public String orderList(@SessionAttribute(name = SessionConst.MEMBER_ID) Long memberId,
                            OrderSearchCond orderSearchCond, Pageable pageable,
                            Model model){
        Page<OrderListResponse> orderList = orderService.findAllByMemberId(memberId, orderSearchCond, pageable);
        model.addAttribute("orderList", orderList);

        return "main/myhome/orderlist";
    }

    @GetMapping("/detail/{orderId}")
    public String detailList(@PathVariable Long orderId, Model model,
                             @SessionAttribute(SessionConst.MEMBER_ID) Long memberId){
        Orders findOrder = orderService.findById(orderId, memberId);

        model.addAttribute("order", findOrder);
        return "main/myhome/orderDetail";
    }

}
