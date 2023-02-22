package com.spring.green2209s_08.web.exception;

import com.spring.green2209s_08.web.exception.errorResult.SearchErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageNotFoundException extends RuntimeException {
    private final SearchErrorResult errorResult;
}
