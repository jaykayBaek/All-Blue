package com.spring.green2209s_08.web.member.controller;

import com.spring.green2209s_08.constants.ControllerConst;
import com.spring.green2209s_08.web.member.exception.MemberException;
import com.spring.green2209s_08.web.member.exception.RedisDataNotFoundException;
import com.spring.green2209s_08.web.member.service.MemberService;
import com.spring.green2209s_08.web.member.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final RegisterService registerService;

    @GetMapping("/signup")
    public String signup(){
        return "main/member/registerForm";
    }

//    @GetMapping("/email/verify")
//    public ResponseEntity<StatusResponse> emailCommit(
//            @RequestParam String token, HttpServletRequest request){
//
//        MemberRegisterRequest memberRegisterRequest = registerService.validateToken(token);
//        String ipAddress = request.getRemoteAddr();
//
//        memberService.register(memberRegisterRequest, ipAddress);
//
//        registerService.removeData(token);
//
//        StatusResponse statusResponse = new StatusResponse(
//                HttpStatus.CREATED.toString(), "회원가입 완료", "TRUE"
//        );
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(statusResponse);
//    }

    @GetMapping("/email/verify")
    public String emailCommit(
            @RequestParam String token, HttpServletRequest request){

        MemberRegisterRequest memberRegisterRequest = registerService.validateToken(token);
        String ipAddress = request.getRemoteAddr();
        memberService.register(memberRegisterRequest, ipAddress);
        registerService.removeData(token);

        return "main/member/emailVerify";
    }

    @ExceptionHandler(RedisDataNotFoundException.class)
    public String handleMemberException(RedisDataNotFoundException e){
        return "/error/custom/tokenNotFound";
    }

    @GetMapping("/email/send")
    public String emailSend(String email,
                            Model model){
        model.addAttribute("email", email);
        return "main/member/emailSend";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }
}
