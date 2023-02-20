package com.spring.green2209s_08.web.exception;

import com.spring.green2209s_08.web.exception.errorResult.InterceptorErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VendorInterceptorException extends RuntimeException {
    private final InterceptorErrorResult errorResult;

}
