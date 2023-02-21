package com.spring.green2209s_08.web.controller.member;

import com.spring.green2209s_08.web.controller.StatusResponse;
import lombok.Getter;

@Getter
public class MemberEditStatusResponse extends StatusResponse {
    private final MemberEditResponse response;
    public MemberEditStatusResponse(String CODE, String MESSAGE, String SUCCESS, MemberEditResponse response) {
        super(CODE, MESSAGE, SUCCESS);
        this.response = response;
    }
}
