package com.spring.green2209s_08.web.member.domain;

import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void 회원가입_일단성공() {
        //given
        Member member = Member.builder().id(1L).name("백정광").build();

        //when
        Member result = memberRepository.save(member);

        //then
        assertThat(result.getName()).isEqualTo(member.getName());
    }
}