package com.spring.green2209s_08.web.controller.member;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.exception.RedisDataNotFoundException;
import com.spring.green2209s_08.web.repository.MemberRepository;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signup(){
        return "main/member/registerForm";
    }

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

    @GetMapping("/account/modify")
    public String accountModifyForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);

        Member findMember = memberRepository.findById(memberId).get();

        String email = findMember.getEmail();
        model.addAttribute("email", email);
        return "main/member/editMemberInfo";
    }


}