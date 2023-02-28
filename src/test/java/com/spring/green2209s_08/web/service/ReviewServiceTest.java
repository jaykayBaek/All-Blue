package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.myhome.ReviewRequest;
import com.spring.green2209s_08.web.domain.*;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.ReviewException;
import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.ReviewErrorResult;
import com.spring.green2209s_08.web.repository.*;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    void 리뷰작성실패_멤버아이디찾을수없음() {
        Member member = Member.builder()
                .build();
        memberRepository.save(member);
        //given
        ReviewRequest request = new ReviewRequest(
                1L, "test", "test", 5L
        );

        //when
        MemberException e = assertThrows(MemberException.class, () ->
                reviewService.writeReview(77L, request)
        );

        //then
        assertThat(e.getErrorResult().MEMBER_NOT_FOUND)
                .isEqualTo(MemberErrorResult.MEMBER_NOT_FOUND);
    }

    @Test
    void 리뷰작성실패_item찾을수없음() {
        //given
        Member member = Member.builder()
                .name("tester")
                .build();
        memberRepository.save(member);
        ReviewRequest request = new ReviewRequest(
                1L, "test", "test", 5L
        );

        //when
        ItemException e = assertThrows(ItemException.class, () ->
                reviewService.writeReview(member.getId(), request)
        );

        //then
        assertThat(e.getErrorResult()).isEqualTo(ItemErrorResult.ITEM_NOT_FOUND);
    }

//    @Test
//    void 리뷰작성실패_구매하지않은상품() {
//        //given
//        Member tester = Member.builder()
//                .name("tester")
//                .build();
//        memberRepository.save(tester);
//
//        Member target = Member.builder()
//                .name("target")
//                .build();
//        memberRepository.save(target);
//
//        Product product = Product
//                .getProduct("test", "test", 1, 1, 1, 1, "test", LocalDate.now());
//        itemRepository.save(product);
//
//        OrderItem orderItem = OrderItem.builder()
//                .item(product)
//                .build();
//        orderItemRepository.save(orderItem);
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(orderItem);
//        Orders orders = Orders.builder()
//                .member(tester)
//                .orderItems(orderItems)
//                .build();
//        orderRepository.save(orders);
//
//        ReviewRequest request = new ReviewRequest(
//                product.getId(), "test", "test", 5L
//        );
//
//        //when
//        ReviewException e = assertThrows(ReviewException.class, () ->
//                reviewService.writeReview(target.getId(), request)
//        );
//
//        //then
//        assertThat(e.getErrorResult()).isEqualTo(ReviewErrorResult.FORBIDDEN);
//    }


}