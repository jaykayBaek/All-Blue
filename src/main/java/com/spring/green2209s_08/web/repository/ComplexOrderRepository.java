package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.order.OrderListResponse;
import com.spring.green2209s_08.web.controller.order.OrderSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComplexOrderRepository {
    Page<OrderListResponse> findAllByMemberId(Long memberId, OrderSearchCond condition, Pageable pageable);

}
