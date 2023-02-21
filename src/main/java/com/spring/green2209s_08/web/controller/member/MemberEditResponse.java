package com.spring.green2209s_08.web.controller.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberEditResponse {
    private String email;
    private String name;
    private String phoneNo;
}
