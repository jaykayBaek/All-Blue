package com.spring.green2209s_08.web.controller.wishlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
import com.spring.green2209s_08.web.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistApiController {

    private final WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<StatusResponse> add(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId,
                                              @RequestParam Long itemId, @RequestParam Integer quantity,
                                              HttpServletResponse response, HttpServletRequest request) throws JsonProcessingException {
        /* --- 만약 로그인 안 했으면 쿠키로 장바구니에 담기게 하고, 로그인 했으면 db에 장바구니를 담고자 한다. --- */
        Optional<Long> optMemberId = Optional.ofNullable(memberId);

        if(isNotLoginMember(optMemberId)){
            Cookie cookie = wishlistService.addItemToWishlist(itemId, quantity, request);
            response.addCookie(cookie);
        } else{
            wishlistService.addItemToWishlist(itemId, quantity, memberId);
        }

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.CREATED.toString(), "장바구니 추가 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusResponse);
    }

    @DeleteMapping("/{itemId}")
    ResponseEntity<StatusResponse> removeItemInWishlist(@PathVariable Long itemId, @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId){
        Optional<Long> optMemberId = Optional.ofNullable(memberId);
        Optional<Long> optItemId = Optional.ofNullable(itemId);

        if(isNotLoginMember(optMemberId)){
            throw new MemberException(MemberErrorResult.UNAUTHORIZED);
        }
        if(optItemId.isEmpty()){
            throw new ItemException(ItemErrorResult.ITEM_NOT_FOUND);
        }

        wishlistService.deleteItemInWishlist(itemId, memberId);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "장바구니 상품 제거 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PostMapping("/count")
    public Long wishlistCountForView(@SessionAttribute(name = SessionConst.MEMBER_ID) Long memberId){
        return wishlistService.countWishlist(memberId);
    }

    @PatchMapping
    public ResponseEntity<StatusResponse> updateQuantity(
            @SessionAttribute(name = SessionConst.MEMBER_ID) Long memberId,
            @RequestParam Long itemId, @RequestParam Integer quantity){
        wishlistService.updateQuantity(memberId, itemId, quantity);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "장바구니 수량 수정 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    private static boolean isNotLoginMember(Optional<Long> optMemberId) {
        return optMemberId.isEmpty();
    }

}
