package com.spring.green2209s_08.web.exception;

import com.spring.green2209s_08.web.exception.errorResult.OrderErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.ReviewErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewException extends RuntimeException {
    private final ReviewErrorResult errorResult;}
