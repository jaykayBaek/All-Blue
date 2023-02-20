package com.spring.green2209s_08.web.exception;

import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException{
    private final MemberErrorResult errorResult;
}
