package com.spring.green2209s_08.web.exception;

import com.spring.green2209s_08.web.exception.errorResult.OrderErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderException extends IllegalStateException {
    private final OrderErrorResult errorResult;
}
