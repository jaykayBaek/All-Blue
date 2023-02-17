package com.spring.green2209s_08.web.exception.errorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ItemErrorResult {
    ITEM_EDIT_FAIL_VENDOR_NOT_MATCH(HttpStatus.BAD_REQUEST, "상품을 업로드한 판매자가 아닙니다."),
    ITEM_NOT_FOUND(HttpStatus.BAD_REQUEST, "상품 코드를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
