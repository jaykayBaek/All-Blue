package com.spring.green2209s_08.web.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.search.ItemDto;
import com.spring.green2209s_08.web.controller.search.ItemSearchCond;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static com.querydsl.jpa.JPAExpressions.select;
import static com.spring.green2209s_08.web.domain.QFish.fish;
import static com.spring.green2209s_08.web.domain.QItem.item;
import static com.spring.green2209s_08.web.domain.QItemImage.*;
import static com.spring.green2209s_08.web.domain.QProduct.product;
import static com.spring.green2209s_08.web.domain.QReview.review;
import static org.hibernate.internal.util.StringHelper.isEmpty;


@Slf4j
public class ComplexItemSearchRepositoryImpl implements ComplexItemSearchRepository{

    private final JPAQueryFactory queryFactory;

    public ComplexItemSearchRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemDto> findItemsByCond(ItemSearchCond condition, Pageable pageable) {
        List<ItemDto> content = queryFactory.
                select(Projections.fields(ItemDto.class,
                                item.id.as("id"), item.itemName.as("itemName"), item.salePrice.as("salePrice"),
                                ExpressionUtils.as(
                                        select(review.id.count())
                                                .from(review)
                                                .where(review.item.eq(item)
                                                ), "reviewCount"),
                                ExpressionUtils.as(
                                        select(review.reviewRating.avg())
                                                .from(review)
                                                .where(review.item.eq(item)
                                                ), "likeAvg"),
                                itemImage.savedImageName.as("savedImageName")
                        )
                )
                .from(item)
                .leftJoin(fish).on(fish.eq(item))
                .leftJoin(product).on(product.eq(item))
                .leftJoin(itemImage).on(itemImage.thumbnailImage.isTrue()
                        .and(itemImage.item.eq(item)))
                .where(
                        queryEq(condition.getQuery()),

                        item.itemStatus.eq(ItemStatus.APPROVAL),
                        parentIdEq(condition.getParentId()),
                        grandchildIdEq(condition.getGrandchildId()),
                        orderByEq(condition.getOrderBy())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(item.count())
                .from(item)
                .fetchOne();

        return new PageImpl<ItemDto>(content, pageable, total);
    }

    public List<ItemDto> findItemsByCond(ItemSearchCond condition) {
        return queryFactory.
                select(Projections.fields(ItemDto.class,
                        item.id, item.itemName, item.salePrice,
                            ExpressionUtils.as(
                                    select(review.id.count())
                                            .from(review)
                                            .where(review.item.eq(item)
                                            ), "reviewCount"),
                            ExpressionUtils.as(
                                select(review.reviewRating.avg())
                                        .from(review)
                                        .where(review.item.eq(item)
                                        ), "likeAvg"),
                        itemImage.savedImageName.as("savedImageName")
                        )
                )
                .from(item)
                .join(fish).on(fish.eq(item))
                .join(product).on(product.eq(item))
                .join(itemImage).on(itemImage.thumbnailImage.isTrue()
                                .and(itemImage.item.eq(item)))
                .where(
                        item.itemStatus.eq(ItemStatus.APPROVAL),
                        queryEq(condition.getQuery()),
                        parentIdEq(condition.getParentId()),
                        grandchildIdEq(condition.getGrandchildId()),
                        orderByEq(condition.getOrderBy())
                )
                .fetch();
    }


    private BooleanExpression queryEq(String query) {
        return isEmpty(query) ? null : item.itemName.startsWith(query);
    }
    private BooleanExpression parentIdEq(String parentId) {
        return isEmpty(parentId) ? null : item.category.parentId.eq(parentId);
    }

    private BooleanExpression grandchildIdEq(String grandchildId) {
        return isEmpty(grandchildId) ? null : item.category.grandchildId.eq(grandchildId);
    }


    private BooleanExpression orderByEq(String orderBy) {
        return null;
    }
}
