package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.myhome.ReviewRequest;
import com.spring.green2209s_08.web.controller.order.OrderListResponse;
import com.spring.green2209s_08.web.domain.*;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.ReviewException;
import com.spring.green2209s_08.web.exception.errorResult.ItemErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.MemberErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.ReviewErrorResult;
import com.spring.green2209s_08.web.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void writeReview(Long memberId, ReviewRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(MemberErrorResult.MEMBER_NOT_FOUND));

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new ItemException(ItemErrorResult.ITEM_NOT_FOUND));

//        List<Long> ordersIdList = orderRepository.findAllByMemberId(memberId)
//                .stream().map(o -> o.getId())
//                .collect(Collectors.toList());
//        List<OrderItem> orderItems = orderItemRepository.findAllByOrdersId(ordersIdList);
//
//        for (OrderItem orderItem : orderItems) {
//            if(!orderItem.getId().equals(request.getItemId())){
//                throw new ReviewException(ReviewErrorResult.FORBIDDEN);
//            }
//        }

        Review review = Review.builder()
                .headLine(request.getHeadLine())
                .content(request.getContent())
                .reviewRating(request.getReviewRating())
                .item(item)
                .member(member)
                .build();

        reviewRepository.save(review);
    }
}
