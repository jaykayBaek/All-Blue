package com.spring.green2209s_08.web.controller.checkout;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.WishlistService;
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
    private final MemberService memberService;
    private final ItemService itemService;
    private final WishlistService wishlistService;

    @GetMapping
    public String checkoutForm(@RequestParam List<Long> itemIdList,
                               @SessionAttribute(name = SessionConst.MEMBER_ID) Long memberId){

        CheckoutMemberDto checkoutMemberDto = new CheckoutMemberDto();
        CheckoutItemDto checkoutItemDto;
        CheckoutTotalPriceDto checkoutTotalPriceDto;

        return "main/checkout/checkoutForm";
    }
}
