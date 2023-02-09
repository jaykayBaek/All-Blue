package com.spring.green2209s_08.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VendorException extends RuntimeException{
    private final VendorErrorResult errorResult;
}
