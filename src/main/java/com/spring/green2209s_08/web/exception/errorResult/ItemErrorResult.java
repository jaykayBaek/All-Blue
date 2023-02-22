package com.spring.green2209s_08.web.exception.errorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ItemErrorResult {
    ITEM_EDIT_FAIL_VENDOR_NOT_MATCH(HttpStatus.BAD_REQUEST, "상품을 업로드한 판매자가 아닙니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "상품 코드를 찾을 수 없습니다."),
    ALREADY_WRITE_ITEM_QUERY(HttpStatus.BAD_REQUEST, "하나의 상품에는 하나의 문의글만 작성할 수 있습니다.");

    private final HttpStatus status;
    private final String message;
}