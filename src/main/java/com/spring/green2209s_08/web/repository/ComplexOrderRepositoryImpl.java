package com.spring.green2209s_08.web.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.order.OrderListResponse;
import com.spring.green2209s_08.web.controller.order.OrderSearchCond;
import com.spring.green2209s_08.web.controller.vandorManagement.ManageDeliveryCond;
import com.spring.green2209s_08.web.controller.vandorManagement.ManageDeliveryDto;
import com.spring.green2209s_08.web.domain.DeliveryStatus;
import com.spring.green2209s_08.web.domain.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.querydsl.core.types.Projections.fields;
import static com.spring.green2209s_08.web.domain.QItem.*;
import static com.spring.green2209s_08.web.domain.QMember.*;
import static com.spring.green2209s_08.web.domain.QOrderItem.*;
import static com.spring.green2209s_08.web.domain.QOrders.orders;
import static com.spring.green2209s_08.web.domain.QVendor.*;
import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
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
                .where(
                        orders.member.id.eq(memberId),
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


    /**
     *  select
     *  orders_id, i.item_name, count(i.item_name), o.recipient, o.address, o.zipcode, o.phone_no,
     *  created_time, delivery_status
     *  from orders o
     *  join order_item oi on oi.orders_orders_id = orders_id
     *  join item i on i.item_id = oi.item_id
     *  join vendor v on i.vendor_id = v.vendor_id
     *  join member m on m.member_id = o.member_member_id
     *  where v.vendor_id = 2
     *  group by i.item_id;
     */
    @Override
    public List<ManageDeliveryDto> manageDeliveryList(Long vendorId, Pageable pageable, ManageDeliveryCond condition) {
        return queryFactory
                .select(
                        fields(ManageDeliveryDto.class,
                        orders.id.as("ordersId"), orders.impUid, item.itemName,
                        item.itemName.count().as("selectedQuantity"), orders.recipient, orders.address, orders.zipcode,
                        orders.phoneNo, orders.createdDate, orders.deliveryStatus
                ))
                .from(orders)
                .join(orderItem).on(orderItem.orders.id.eq(orders.id))
                .join(item).on(item.id.eq(orderItem.id))
                .join(vendor).on(item.vendor.id.eq(vendor.id))
                .join(member).on(member.id.eq(orders.member.id))
                .where(
                        vendor.id.eq(vendorId),
                        orders.orderStatus.eq(OrderStatus.PAYMENT_OK),
                        deliveryStatusEq(condition.getDeliveryStatus())
                )
                .orderBy(orders.createdDate.desc())
                .groupBy(item.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    private BooleanExpression deliveryStatusEq(DeliveryStatus deliveryStatus) {
        return isEmpty(deliveryStatus) ? null : orders.deliveryStatus.eq(deliveryStatus);
    }

    private BooleanExpression queryEq(String query) {
        return isEmpty(query) ? null : orders.orderItemName.contains(query);
    }
}
