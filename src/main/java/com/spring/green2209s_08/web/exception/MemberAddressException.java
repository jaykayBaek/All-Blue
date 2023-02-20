package com.spring.green2209s_08.web.exception;

import com.spring.green2209s_08.web.exception.errorResult.AddressErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberAddressException extends RuntimeException {
    private final AddressErrorResult errorResult;
}
