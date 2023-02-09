package com.spring.green2209s_08.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/list")
    public String orderList(){
        return "main/order/orderlist";
    }
}
