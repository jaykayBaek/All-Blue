package com.spring.green2209s_08.web.controller.wishlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
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
            Cookie cookie = wishlistService.addWishlist(itemId, quantity, request);
            response.addCookie(cookie);
        }

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.CREATED.toString(), "장바구니 추가 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusResponse);
    }

    private static boolean isNotLoginMember(Optional<Long> optMemberId) {
        return optMemberId.isEmpty();
    }

}
