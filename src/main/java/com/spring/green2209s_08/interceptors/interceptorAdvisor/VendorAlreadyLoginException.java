package com.spring.green2209s_08.interceptors.interceptorAdvisor;

import com.spring.green2209s_08.web.exception.InterceptorException;
import com.spring.green2209s_08.web.exception.errorResult.InterceptorErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class VendorAlreadyLoginException extends InterceptorException {
    public VendorAlreadyLoginException(InterceptorErrorResult errorResult) {
        super(errorResult);
    }
}
