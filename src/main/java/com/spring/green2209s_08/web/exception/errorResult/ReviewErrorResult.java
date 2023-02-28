package com.spring.green2209s_08.web.exception.errorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorResult {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "비정상적인 접근입니다. 권한 인증을 위해 다시 로그인해주세요."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "리뷰 작성 권한이 없습니다. 상품을 구매하지 않았거나, 이미 리뷰를 작성했습니다.")
    ;
    private final HttpStatus status;
    private final String message;
}
