package com.spring.green2209s_08.web.controller.order;

import com.spring.green2209s_08.web.domain.OrderItem;
import com.spring.green2209s_08.web.repository.OrderRepository;
import com.spring.green2209s_08.web.service.OrderItemService;
import com.spring.green2209s_08.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @GetMapping("/list")
    public String orderList(){
        return "main/myhome/orderlist";
    }

}
