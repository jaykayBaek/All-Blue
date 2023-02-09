package com.spring.green2209s_08.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EmailErrorResult {
    FAIL_SEND_EMAIL(HttpStatus.BAD_REQUEST, "이메일 전송 실패");

    private final HttpStatus status;
    private final String message;

}
