package com.spring.green2209s_08.web.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException{
    private final MemberErrorResult errorResult;
}
