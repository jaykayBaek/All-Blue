package com.spring.green2209s_08.web.member.service;

import com.spring.green2209s_08.web.member.domain.Member;
import com.spring.green2209s_08.web.member.exception.MemberErrorResult;
import com.spring.green2209s_08.web.member.exception.MemberException;
import com.spring.green2209s_08.web.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    private final static String EMAIL = "test@naver.com";

    @Test
    void 회원가입실패_회월탈퇴30일안지났는데가입하려함() {
        //given
        Member member = Member.builder()
                .email(EMAIL)
                .leavedTime(LocalDateTime.now())
                .build();
        memberRepository.save(member);

        //when
        Member tester = Member.builder()
                .email(EMAIL)
                .build();
        MemberException e = assertThrows(MemberException.class, () ->
                memberService.validateEmail(tester.getEmail())
        );
        //then
        assertThat(e.getErrorResult()).isEqualTo(MemberErrorResult.LEAVE_PENALTY_PERIOD);
    }

    @Test
    void 로그인실패_() {
        //given

        //when

        //then

    }
}