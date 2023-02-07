package com.spring.green2209s_08.web.member.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatusResponse {
    private final String CODE;
    private final String MESSAGE;
    private final String SUCCESS;
}
