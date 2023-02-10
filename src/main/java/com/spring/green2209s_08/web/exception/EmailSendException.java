package com.spring.green2209s_08.web.exception;

import com.spring.green2209s_08.web.exception.errorResult.EmailErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailSendException extends RuntimeException {
    private final EmailErrorResult errorResult;

}
