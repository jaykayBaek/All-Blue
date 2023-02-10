package com.spring.green2209s_08.web.exception.errorResult;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InterceptorErrorResult {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    ALREADY_LOGIN(HttpStatus.BAD_REQUEST, "이미 로그인했습니다.");

    private final HttpStatus status;
    private final String message;
}
