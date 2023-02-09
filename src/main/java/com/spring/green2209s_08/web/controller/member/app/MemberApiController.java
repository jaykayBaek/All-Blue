package com.spring.green2209s_08.web.controller.member.app;

import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("verify/email")
    public ResponseEntity<StatusResponse> verifyEmail(String email){
        memberService.validateEmail(email);
        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "이메일 검증 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }
}
