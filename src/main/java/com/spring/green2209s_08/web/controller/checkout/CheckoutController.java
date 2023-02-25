package com.spring.green2209s_08.web.controller.checkout;

import com.spring.green2209s_08.web.constants.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @GetMapping
    public String checkoutForm(@RequestParam List<Map<Long, Integer>> checkoutItem,
                               @SessionAttribute(name = SessionConst.MEMBER_ID) Long memberId){
        for (Map<Long, Integer> longIntegerMap : checkoutItem) {

        }

        return "main/checkout/checkoutForm";
    }
}
