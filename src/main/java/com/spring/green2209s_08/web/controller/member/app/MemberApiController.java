package com.spring.green2209s_08.web.controller.member.app;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.controller.vendor.dto.ChangePasswordRequest;
import com.spring.green2209s_08.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/verify/email")
    public ResponseEntity<StatusResponse> verifyEmail(String email){

        memberService.validateEmail(email);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "이메일 검증 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PatchMapping("/account/name")
    public ResponseEntity<StatusResponse> changeName(@RequestParam String name,
                                                     @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId){
        memberService.changeName(memberId, name);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "이름 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }
    @PatchMapping("/account/password")
    public ResponseEntity<StatusResponse> changePassword(
            @ModelAttribute ChangePasswordRequest passwordRequest,
            @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId){

        memberService.matchPasswordForChangePassword(passwordRequest.getPassword(), memberId);
        memberService.changePassword(memberId, passwordEncoder.encode(passwordRequest.getNewPassword()));

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "비밀번호 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PatchMapping("/account/phone-no")
    public ResponseEntity<StatusResponse> changePhoneNo(
            @RequestParam String phoneNo, @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId){

        memberService.changePhoneNo(memberId, phoneNo);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "휴대폰 번호 변경 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

}
