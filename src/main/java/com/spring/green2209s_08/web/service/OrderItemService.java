package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.myhome.ReviewItemDto;
import com.spring.green2209s_08.web.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    public List<ReviewItemDto> reviewPage(Long memberId) {
        return orderItemRepository.reviewPage(memberId);
    }

    public List<ReviewItemDto> reviewPageWrote(Long memberId) {
        return orderItemRepository.reviewPageWrote(memberId);
    }
}
