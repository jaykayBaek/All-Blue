package com.spring.green2209s_08.web.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.myhome.ReviewItemDto;
import com.spring.green2209s_08.web.domain.DeliveryStatus;
import com.spring.green2209s_08.web.domain.QReview;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.core.types.Projections.fields;
import static com.querydsl.jpa.JPAExpressions.select;
import static com.spring.green2209s_08.web.domain.QItem.item;
import static com.spring.green2209s_08.web.domain.QItemImage.itemImage;
import static com.spring.green2209s_08.web.domain.QOrderItem.orderItem;
import static com.spring.green2209s_08.web.domain.QOrders.orders;
import static com.spring.green2209s_08.web.domain.QReview.review;

public class ComplexOrderItemRepositoryImpl implements ComplexOrderItemRepository {

    private JPAQueryFactory queryFactory;

    public ComplexOrderItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ReviewItemDto> reviewPage(Long memberId) {
        return queryFactory.
                select(
                        fields(ReviewItemDto.class,
                                orders.id.as("ordersId"), item.id.as("itemId"),
                                itemImage.savedImageName, item.itemName
                        )
                )
                .from(orderItem)
                .join(orders).on(orders.id.eq(orderItem.orders.id))
                .join(item).on(item.id.eq(orderItem.item.id))
                .join(itemImage).on(
                        itemImage.item.id.eq(item.id),
                        itemImage.thumbnailImage.isTrue()
                )
                .where(
                        orderItem.item.id
                                .notIn(
                                        select(review.item.id)
                                        .from(review)
                                        .where(review.member.id.eq(memberId))
                        ),
                        orders.deliveryStatus.eq(DeliveryStatus.COMPLETE),
                        orders.member.id.eq(memberId)
                )
                .fetch();
    }

    @Override
    public List<ReviewItemDto> reviewPageWrote(Long memberId) {
        return queryFactory.
                select(
                        fields(ReviewItemDto.class,
                                orders.id.as("ordersId"), item.id.as("itemId"),
                                itemImage.savedImageName, item.itemName,
                                review.reviewRating
                        )
                )
                .from(orderItem)
                .join(orders).on(orders.id.eq(orderItem.orders.id))
                .join(item).on(item.id.eq(orderItem.item.id))
                .join(itemImage).on(
                        itemImage.item.id.eq(item.id),
                        itemImage.thumbnailImage.isTrue()
                )
                .join(review).on(review.item.id.eq(item.id))
                .where(
                        orderItem.item.id
                                .in(
                                        select(review.item.id)
                                                .from(review)
                                                .where(review.member.id.eq(memberId))
                                ),
                        orders.deliveryStatus.eq(DeliveryStatus.COMPLETE),
                        orders.member.id.eq(memberId)
                )
                .fetch();
    }
}
