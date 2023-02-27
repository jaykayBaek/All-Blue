package com.spring.green2209s_08.web.controller.checkout;

import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.domain.Address;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.domain.Wishlist;
import com.spring.green2209s_08.web.service.AddressService;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutApiController {

    private final MemberService memberService;
    private final AddressService addressService;
    private final WishlistService wishlistService;
    private final ItemService itemService;

    @PostMapping("/order-info")
    public ResponseEntity<StatusResponse> orderInfo(
            @RequestParam Long memberId, @RequestParam Long addressId,
            @RequestParam(name="wishlistIdList[]") List<Long> wishlistIdList,
            @RequestParam(name = "itemIdList[]") List<Long> itemIdList){

        Member findMember = memberService.findById(memberId);
        Address findAddress = addressService.findById(addressId);
//        List<Wishlist> findWishlist = wishlistService.findAll(wishlistIdList);

        List<ItemOrderResponse> itemOrderResponse = getItemOrderResponse(itemIdList);

        CheckoutMemberDto memberOrderResponse = getMemberOrderResponse(findMember);

        AddressOrderResponse addressOrderResponse = getAddressOrderResponse(findAddress);

        Integer deliveryTotal = wishlistService.findTotalDeliveryPriceForCheckout(itemIdList, memberId);
        Integer saleTotal = wishlistService.findTotalSalePriceForCheckout(itemIdList, memberId);
        CheckoutTotalPriceDto totalPriceResponse = getTotalPriceResponse(deliveryTotal, saleTotal);

        CheckoutStatusResponse checkoutStatusResponse = new CheckoutStatusResponse(
                HttpStatus.OK.toString(), "주문 정보 제공 성공", "TRUE",
                itemOrderResponse, memberOrderResponse, addressOrderResponse, totalPriceResponse);

        return ResponseEntity.status(HttpStatus.OK)
                .body(checkoutStatusResponse);
    }

    private static CheckoutTotalPriceDto getTotalPriceResponse(Integer deliveryTotal, Integer saleTotal) {
        return new CheckoutTotalPriceDto(
                saleTotal + deliveryTotal, deliveryTotal, saleTotal
        );
    }

    private List<ItemOrderResponse> getItemOrderResponse(List<Long> itemIdList) {
        return itemService.findAll(itemIdList).stream()
                .map(i -> new ItemOrderResponse(
                        i.getId(), i.getItemName(), i.getSalePrice()))
                .collect(Collectors.toList());
    }

    private static CheckoutMemberDto getMemberOrderResponse(Member findMember) {
        return new CheckoutMemberDto(
                findMember.getId(), findMember.getName(), findMember.getEmail(), findMember.getPhoneNo()
        );
    }

    private static AddressOrderResponse getAddressOrderResponse(Address findAddress) {
        return new AddressOrderResponse(
                findAddress.getId(), findAddress.getRecipient(), findAddress.getPhoneNo(), findAddress.getZipcode(),
                findAddress.getAddress(), findAddress.getDetail()
        );
    }

    @PostMapping("/order-uid")
    public ResponseEntity<StatusResponse> orderUid(HttpServletRequest request){

        String orderUid = UUID.randomUUID().toString();
//        HttpSession session = request.getSession();
//        session.setAttribute(SessionConst.ORDERUID, orderUid);

        OrderUidStatusResponse statusResponse = new OrderUidStatusResponse(
                HttpStatus.CREATED.toString(), "주문 UUID 생성", "TRUE", orderUid
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusResponse);
    }
}
