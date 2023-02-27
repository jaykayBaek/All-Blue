package com.spring.green2209s_08.web.exception.errorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum OrderErrorResult {
    TRY_CANCEL_ORDER_IN_ILLEGALSTATE(HttpStatus.BAD_REQUEST, "배송 중이거나, 배송 완료된 상품입니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "비정상적인 접근이거나, 세션이 만료되었습니다. 권한 인증을 위해 다시 로그인해주세요.");

    private final HttpStatus status;
    private final String message;
}
