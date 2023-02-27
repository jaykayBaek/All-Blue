package com.spring.green2209s_08.web.exception;

import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.WishlistErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WishlistException extends RuntimeException {
    private final WishlistErrorResult errorResult;

}
