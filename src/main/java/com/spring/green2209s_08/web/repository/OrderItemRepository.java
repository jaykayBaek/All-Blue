package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Long> {
}
