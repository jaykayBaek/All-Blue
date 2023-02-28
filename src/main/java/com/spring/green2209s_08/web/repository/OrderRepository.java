package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Orders;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long>, ComplexOrderRepository {
    @BatchSize(size = 100)
    @Query("select o from Orders o " +
            "join OrderItem oi on oi.orders.id = o.id " +
            "join Item i on i.id = oi.item.id " +
            "join Vendor v on v.id = i.vendor.id " +
            "where o.impUid = :impUid and v.id = :vendorId")
    Optional<Orders> findByImpUidAndVendorId(String impUid, Long vendorId);

    @Query("select o from Orders o " +
            "join OrderItem oi on oi.orders.id = o.id " +
            "join Item i on oi.item.id = i.id " +
            "where o.member.id = :memberId and i.id = :itemId " +
            "group by i.id")
    Optional<Orders> findOrderForReview(Long memberId, Long itemId);
}
