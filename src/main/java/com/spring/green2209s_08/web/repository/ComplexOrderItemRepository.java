package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.myhome.ReviewItemDto;

import java.util.List;

public interface ComplexOrderItemRepository {
    List<ReviewItemDto> reviewPage(Long memberId);

    List<ReviewItemDto> reviewPageWrote(Long memberId);
}
