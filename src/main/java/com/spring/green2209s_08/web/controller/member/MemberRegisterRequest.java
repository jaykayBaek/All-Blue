package com.spring.green2209s_08.web.controller.member;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRegisterRequest {
    private String email;
    private String password;
    private String name;
    private String birthdate;
    private String phoneNo;

    public void changeEncodedPassword(String password) {
        this.password = password;
    }
}
