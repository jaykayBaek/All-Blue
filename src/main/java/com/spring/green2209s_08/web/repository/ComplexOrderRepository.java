package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.myhome.ReviewItemDto;
import com.spring.green2209s_08.web.controller.order.OrderListResponse;
import com.spring.green2209s_08.web.controller.order.OrderSearchCond;
import com.spring.green2209s_08.web.controller.vandorManagement.ManageDeliveryCond;
import com.spring.green2209s_08.web.controller.vandorManagement.ManageDeliveryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComplexOrderRepository {
    Page<OrderListResponse> findAllByMemberId(Long memberId, OrderSearchCond condition, Pageable pageable);

    List<ManageDeliveryDto> manageDeliveryList(Long vendorId, Pageable pageable, ManageDeliveryCond condition);

    List<ReviewItemDto> reviewPage(Long memberId);
}
