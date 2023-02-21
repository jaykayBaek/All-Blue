package com.spring.green2209s_08.web.controller.member;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.controller.member.MemberLoginRequest;
import com.spring.green2209s_08.web.controller.member.MemberRegisterRequest;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.domain.MemberRegister;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.RegisterEmailSend;
import com.spring.green2209s_08.web.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberRestController {

    private final MemberService memberService;
    private final RegisterEmailSend registerEmailSend;
    private final RegisterService registerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<StatusResponse> register(
            @Validated MemberRegisterRequest request,
            BindingResult bindingResult){

        memberService.validateEmail(request.getEmail());
        
        // 비밀번호 암호화
        request.changeEncodedPassword(passwordEncoder.encode(request.getPassword()));
        
        /* --- 이메일 전송 --- */
        emailSendAndCreateTempData(request);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "메세지 전송 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    private void emailSendAndCreateTempData(MemberRegisterRequest request) {
        String key = registerEmailSend.sendMail(request.getEmail());

        MemberRegister data = MemberRegister.builder()
                .name(request.getName())
                .birthdate(request.getBirthdate())
                .password(request.getPassword())
                .email(request.getEmail())
                .phoneNo(request.getPhoneNo())
                .build();

        registerService.save(key, data);
    }

    @PostMapping("/login")
    public ResponseEntity<StatusResponse> login(
            @Validated MemberLoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request){
        Long memberId = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.MEMBER_ID, memberId);

        memberService.updateVisitTimeAndIpAddress(memberId, LocalDateTime.now(), request.getRemoteAddr());

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "로그인 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PostMapping("/account/modify")
    public ResponseEntity<StatusResponse> modifyAccountPasswordCheck(@RequestParam String password,
                                                                     HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);

        Member member = memberService.passwordCheck(memberId, password);

        MemberEditResponse response = new MemberEditResponse(member.getEmail(), member.getName(), member.getPhoneNo());

        MemberEditStatusResponse statusResponse = new MemberEditStatusResponse(
                HttpStatus.OK.toString(), "본인 확인 완료", "TRUE", response
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

}
