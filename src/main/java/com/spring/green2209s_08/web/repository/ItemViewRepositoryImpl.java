package com.spring.green2209s_08.web.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.domain.QItem;
import com.spring.green2209s_08.web.domain.QVendor;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import com.spring.green2209s_08.web.service.dto.ItemCountResponse;

import javax.persistence.EntityManager;

import static com.querydsl.jpa.JPAExpressions.*;
import static com.spring.green2209s_08.web.domain.QItem.*;
import static com.spring.green2209s_08.web.domain.QVendor.*;

public class ItemViewRepositoryImpl implements ItemViewRepository{

    private JPAQueryFactory queryFactory;

    public ItemViewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public ItemCountResponse findUploadItemsCount(Long vendorId) {
        return queryFactory
                .select(Projections.fields(ItemCountResponse.class,
                    item.count().as("uploadItemsCount"),
                        ExpressionUtils.as(select(
                                    item.count()
                            ).from(item)
                             .where(item.vendor.id.eq(vendorId)
                                     .and(item.itemStatus.eq(ItemStatus.APPROVAL))
                             ), "approvalItemsCount"))
                )
                .from(item)
                .where(item.vendor.id.eq(vendorId))
                .fetchOne();
    }
}
