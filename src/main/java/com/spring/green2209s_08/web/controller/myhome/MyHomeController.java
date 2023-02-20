package com.spring.green2209s_08.web.controller.myhome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/home")
public class MyHomeController {
    @GetMapping("/order/list")
    public String orderList(){
        return "main/myhome/orderlist";
    }

    @GetMapping("/cancel-return-exchange/list")
    public String cancelReturnExchangeList(){
        return "main/myhome/cancelReturnExchangeList";
    }




    @GetMapping("/my-address/list")
    public String myAddressList(){
        return "main/myhome/myAddressList";
    }
}
