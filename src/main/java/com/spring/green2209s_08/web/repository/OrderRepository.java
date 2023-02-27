package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long>, ComplexOrderRepository {
}
