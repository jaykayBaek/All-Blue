package com.spring.green2209s_08.web.controller.checkout;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.domain.Wishlist;
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
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final WishlistService wishlistService;

    @GetMapping
    public String checkoutForm(@RequestParam(name = "itemIdList[]") List<Long> itemIdList,
                               @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId){
        Member findMember = memberService.findById(memberId);
        CheckoutMemberDto member = CheckoutMemberDto.builder()
                .memberId(findMember.getId())
                .memberEmail(findMember.getEmail())
                .memberPhoneNo(findMember.getPhoneNo())
                .memberName(findMember.getName())
                .build();

//        List<Wishlist> findWishlist = wishlistService.findByItemIdsAndMemberId(memberId, itemIdList);
//
//        List<CheckoutItemDto> wishlist = findWishlist.stream()
//                .map(w -> CheckoutItemDto.builder()
//                        .itemId(w.getItem().getId())
//                        .itemName(w.getItem().getItemName())
//                        .itemSelectedQuantity(w.getSelectedQuantity())
//                        .build()
//                )
//                .collect(Collectors.toList());
//        List<Long> wishlistIds = findWishlist.stream()
//                .map(Wishlist::getId)
//                .collect(Collectors.toList());

//        itemService.findCheckoutItemAmount(wishlistIds);


        return "main/checkout/checkoutForm";
    }
}
