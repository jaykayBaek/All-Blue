package com.spring.green2209s_08.web.exception.errorResult;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum SearchErrorResult {
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 상품을 찾을 수 없습니다")
    ;

    private final HttpStatus status;
    private final String message;
}
