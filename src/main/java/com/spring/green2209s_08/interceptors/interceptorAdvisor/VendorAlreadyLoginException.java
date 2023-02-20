package com.spring.green2209s_08.interceptors.interceptorAdvisor;

import com.spring.green2209s_08.web.exception.VendorInterceptorException;
import com.spring.green2209s_08.web.exception.errorResult.InterceptorErrorResult;
import lombok.Getter;

@Getter
public class VendorAlreadyLoginException extends VendorInterceptorException {
    public VendorAlreadyLoginException(InterceptorErrorResult errorResult) {
        super(errorResult);
    }
}
