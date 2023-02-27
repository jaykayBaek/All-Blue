package com.spring.green2209s_08.web.exception.errorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum OrderErrorResult {
    TRY_CANCEL_ORDER_IN_ILLEGALSTATE(HttpStatus.BAD_REQUEST, "배송 중이거나, 배송 완료된 상품입니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
