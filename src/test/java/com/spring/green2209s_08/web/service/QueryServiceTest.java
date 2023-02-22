package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.domain.Fish;
import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.Member;
import com.spring.green2209s_08.web.domain.enums.FishSex;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class QueryServiceTest {
    @Autowired
    QueryService queryService;

    @Autowired
    EntityManager em;


    @Test
    void 문의글작성실패_등록된상품없음() {
        //given
        Member member = Member.builder()
                .name("홍길동")
                .build();
        em.persist(member);

        //when
        ItemException e = assertThrows(ItemException.class, () ->
                queryService.writeItemQuery(1L, member.getId(), "test")
        );

        //then
        assertThat(e.getErrorResult().getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(e.getErrorResult().getMessage()).isEqualTo(ItemErrorResult.ITEM_NOT_FOUND.getMessage());
    }

    @Test
    void 문의글작성실패_중복() {
        //given
        Member member = Member.builder()
                .name("홍길동")
                .build();
        Fish fish = Fish.getFish("hello", FishSex.MALE, "미디움", "금붕어",
                10000, 2999, 30, "hello", LocalDate.now());

        em.persist(member);
        em.persist(fish);

        queryService.writeItemQuery(fish.getId(), member.getId(), "test");

        //when
        ItemException e = assertThrows(ItemException.class, () ->
                queryService.writeItemQuery(fish.getId(), member.getId(), "test")
        );
        System.out.println("e.getErrorResult().getMessage() = " + e.getErrorResult().getMessage());

        //then
        assertThat(e.getErrorResult().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(e.getErrorResult().getMessage()).isEqualTo(ItemErrorResult.ALREADY_WRITE_ITEM_QUERY.getMessage());
    }

    @Test
    void 문의글작성성공() {
        //given
        Member member = Member.builder()
                .name("홍길동")
                .build();
        Fish fish = Fish.getFish("hello", FishSex.MALE, "미디움", "금붕어",
                10000, 2999, 30, "hello", LocalDate.now());

        em.persist(member);
        em.persist(fish);

        //when
        Long itemId = queryService.writeItemQuery(fish.getId(), member.getId(), "test");

        //then
        assertThat(itemId).isEqualTo(fish.getId());

    }
}