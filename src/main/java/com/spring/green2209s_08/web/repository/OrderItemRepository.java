package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.myhome.ReviewItemDto;
import com.spring.green2209s_08.web.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Long>, ComplexOrderItemRepository {
    @Query("select oi from OrderItem oi " +
            "where oi.orders.id in(:ordersIdList)")
    List<OrderItem> findAllByOrdersId(List<Long> ordersIdList);
}
