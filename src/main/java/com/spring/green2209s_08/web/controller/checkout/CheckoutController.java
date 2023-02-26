package com.spring.green2209s_08.web.controller.checkout;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.myhome.AddressResponse;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.domain.Wishlist;
import com.spring.green2209s_08.web.service.AddressService;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final MemberService memberService;
    private final WishlistService wishlistService;
    private final AddressService addressService;

    @GetMapping
    public String checkoutForm(@RequestParam(name = "itemIdList[]", required = false) List<Long> itemIdList,
                               @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId,
                               Model model){
        Member findMember = memberService.findById(memberId);

        CheckoutMemberDto member = getMemberDto(findMember);

        List<Wishlist> findWishlist = wishlistService.findByItemIdListAndMemberId(itemIdList, memberId);

        List<CheckoutItemDto> wishlist = getWishlistDto(findWishlist);

        Integer totalSalePrice = wishlistService.findTotalSalePriceForCheckout(itemIdList, memberId);
        Integer totalDeliveryPrice = wishlistService.findTotalDeliveryPriceForCheckout(itemIdList, memberId);

        CheckoutTotalPriceDto billing = getBillingDto(totalSalePrice, totalDeliveryPrice);

        List<AddressResponse> findAddress = addressService.findAllMyAddress(memberId);

        if(!findAddress.isEmpty()){
            AddressResponse address = findAddress.get(0);
            model.addAttribute("address", address);
        }

        model.addAttribute("member", member);
        model.addAttribute("addressList", findAddress);
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("billing", billing);

        return "main/checkout/checkoutForm";
    }

    private static CheckoutTotalPriceDto getBillingDto(Integer totalSalePrice, Integer totalDeliveryPrice) {
        return CheckoutTotalPriceDto.builder()
                .totalSalePrice(totalSalePrice)
                .totalDeliveryPrice(totalDeliveryPrice)
                .totalPrice(totalSalePrice + totalDeliveryPrice)
                .build();
    }

    private static List<CheckoutItemDto> getWishlistDto(List<Wishlist> findWishlist) {
        return findWishlist.stream()
                .map(w -> CheckoutItemDto.builder()
                        .wishlistId(w.getItem().getId())
                        .itemName(w.getItem().getItemName())
                        .itemSelectedQuantity(w.getSelectedQuantity())
                        .build()
                )
                .collect(Collectors.toList());
    }

    private static CheckoutMemberDto getMemberDto(Member findMember) {
        return CheckoutMemberDto.builder()
                .memberId(findMember.getId())
                .memberEmail(findMember.getEmail())
                .memberPhoneNo(findMember.getPhoneNo())
                .memberName(findMember.getName())
                .build();
    }
}
