package com.spring.green2209s_08.web.exception.errorResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum WishlistErrorResult {
    WISHLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "장바구니에서 해당 상품을 찾을 수 없습니다.");
    private final HttpStatus status;
    private final String message;
}
