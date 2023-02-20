package com.spring.green2209s_08.web.controller.vendor.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordRequest {
    private String password;
    private String newPassword;
    private String repeatNewPassword;
}
