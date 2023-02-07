package com.spring.green2209s_08.web.member.controller;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class MemberLoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public void changeEncodedPassword(String password) {
        this.password = password;
    }

}
