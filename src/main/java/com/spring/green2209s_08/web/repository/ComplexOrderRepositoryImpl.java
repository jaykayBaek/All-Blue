package com.spring.green2209s_08.web.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.order.OrderListResponse;
import com.spring.green2209s_08.web.controller.order.OrderSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.querydsl.core.types.Projections.fields;
import static com.spring.green2209s_08.web.domain.QOrders.orders;
import static org.springframework.util.ObjectUtils.isEmpty;

public class ComplexOrderRepositoryImpl implements ComplexOrderRepository{

    private JPAQueryFactory queryFactory;

    public ComplexOrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<OrderListResponse> findAllByMemberId(Long memberId, OrderSearchCond condition, Pageable pageable) {
        List<OrderListResponse> content = queryFactory
                .select(fields(OrderListResponse.class,
                        orders.id.as("ordersId"), orders.createdDate, orders.orderItemName,
                        orders.totalPrice, orders.totalDeliveryPrice
                ))
                .from(orders)
                .where(orders.member.id.eq(memberId),
                        dateBetween(condition.getStartDate(), condition.getEndDate()),
                        queryEq(condition.getQuery())
                )
                .orderBy(orders.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(orders.count())
                .from(orders)
                .where(orders.member.id.eq(memberId))
                .fetchOne();
        return new PageImpl<OrderListResponse>(content, pageable, total);
    }

    private BooleanExpression queryEq(String query) {
        return isEmpty(query) ? null : orders.orderItemName.contains(query);
    }

    private BooleanExpression dateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        if(isEmpty(startDate)){
            return null;
        } else{
            return orders.createdDate.between(startDate, endDate);
        }
    }
}
