package com.spring.green2209s_08.web.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.item.ItemListStatusResponse;
import com.spring.green2209s_08.web.controller.item.UploadItemCond;
import com.spring.green2209s_08.web.controller.item.VendorUploadItemResponse;
import com.spring.green2209s_08.web.domain.enums.ItemStatus;
import com.spring.green2209s_08.web.service.dto.VendorHomeItemCountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static com.spring.green2209s_08.web.domain.QItem.*;
import static org.hibernate.internal.util.StringHelper.isEmpty;

public class ItemViewRepositoryImpl implements ItemViewRepository{

    private JPAQueryFactory queryFactory;

    public ItemViewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public VendorHomeItemCountResponse findUploadItemsCount(Long vendorId) {
        return queryFactory
                .select(Projections.fields(VendorHomeItemCountResponse.class,
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

    @Override
    public Page<VendorUploadItemResponse> findUploadItemList(Long vendorId, Pageable pageable, UploadItemCond condition) {
        List<VendorUploadItemResponse> content = queryFactory
                .select(Projections.fields(VendorUploadItemResponse.class,
                                item.id, item.itemName, item.salePrice.as("price"), item.stockQuantity,
                                item.category.parentId.as("categoryId"), item.category.grandchildName.as("categoryName"),
                                item.uploadDate, item.itemStatus
                        )
                )
                .from(item)
                .where(
                        item.vendor.id.eq(vendorId),
                        queryEq(condition.getQuery()),
                        itemStatusEq(condition.getItemStatus())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long total = queryFactory
                .select(item.count())
                .from(item)
                .fetchOne();


        return new PageImpl<VendorUploadItemResponse>(content, pageable, total);
    }

    @Override
    public ItemListStatusResponse findUploadItemStatusCount(Long vendorId) {
        return queryFactory
                .select(Projections.fields(ItemListStatusResponse.class,
                                ExpressionUtils.as(
                                       select(item.count())
                                               .from(item)
                                               .where(item.vendor.id.eq(vendorId)), "total"
                                ),
                                ExpressionUtils.as(
                                        select(item.count())
                                        .from(item)
                                        .where(item.vendor.id.eq(vendorId)
                                                .and(item.itemStatus.eq(ItemStatus.DECISION_IN_PROCESS))), "decisionInProcess"
                                ),
                                ExpressionUtils.as(
                                        select(item.count())
                                        .from(item)
                                        .where(item.vendor.id.eq(vendorId)
                                                .and(item.itemStatus.eq(ItemStatus.REJECT_APPROVAL))), "reject"
                                ),
                                ExpressionUtils.as(
                                        select(item.count())
                                        .from(item)
                                        .where(item.vendor.id.eq(vendorId)
                                                .and(item.itemStatus.eq(ItemStatus.BLOCK_SELLING))), "blockSelling"
                                ),
                                ExpressionUtils.as(
                                        select(item.count())
                                        .from(item)
                                        .where(item.vendor.id.eq(vendorId)
                                                .and(item.itemStatus.eq(ItemStatus.SHUT_DOWN))), "shutdown"
                                ),
                                ExpressionUtils.as(
                                        select(item.count())
                                        .from(item)
                                        .where(item.vendor.id.eq(vendorId)
                                                .and(item.itemStatus.eq(ItemStatus.APPROVAL))), "approval"
                                )
                        )
                )
                .from(item)
                .where(item.vendor.id.eq(vendorId))
                .fetchFirst();
    }

    private BooleanExpression queryEq(String query) {
        return isEmpty(query) ? null : item.itemName.contains(query);
    }

    private BooleanExpression itemStatusEq(List<ItemStatus> itemStatus) {
        return itemStatus == null ? null : item.itemStatus.in(itemStatus);
    }
}
