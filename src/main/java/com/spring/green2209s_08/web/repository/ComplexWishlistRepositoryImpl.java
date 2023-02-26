package com.spring.green2209s_08.web.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.wishlist.WishlistCond;
import com.spring.green2209s_08.web.controller.wishlist.WishlistViewDto;
import com.spring.green2209s_08.web.domain.Wishlist;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import java.util.List;

import static com.spring.green2209s_08.web.domain.QItem.item;
import static com.spring.green2209s_08.web.domain.QItemImage.itemImage;
import static com.spring.green2209s_08.web.domain.QMember.*;
import static com.spring.green2209s_08.web.domain.QVendor.vendor;
import static com.spring.green2209s_08.web.domain.QVendorLicense.vendorLicense;
import static com.spring.green2209s_08.web.domain.QWishlist.wishlist;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
public class ComplexWishlistRepositoryImpl implements ComplexWishlistRepository{

    private JPAQueryFactory queryFactory;

    public ComplexWishlistRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<WishlistViewDto> findWishlist(WishlistCond condition) {
        return queryFactory
                .select(
                        Projections.fields(WishlistViewDto.class,
                            wishlist.item.id.as("itemId"),
                            itemImage.savedImageName,
                            wishlist.item.itemName,
                            wishlist.item.salePrice,
                            wishlist.item.deliveryPrice,
                            wishlist.item.stockQuantity,
                            wishlist.selectedQuantity,
                            vendorLicense.id.as("vendorLicenseId"),
                            vendorLicense.storeName
                        )
                )
                .from(wishlist)
                .leftJoin(wishlist.item, item)
                    .on(item.id.eq(wishlist.item.id))
                .leftJoin(item.itemImages, itemImage)
                    .on(
                            itemImage.item.id
                                    .eq(item.id)
                                    .and(itemImage.thumbnailImage.isTrue())
                    )
                .leftJoin(item.vendor, vendor)
                .leftJoin(vendor.vendorLicense, vendorLicense)
                .where(
                        isMemberIdEq(condition.getMemberId())
                )
                .fetch();
    }

    @Override
    public List<Wishlist> findAllByItemIdAndMemberId(List<Long> itemIdList, Long memberId) {
        return queryFactory
                .select(wishlist)
                .from(wishlist)
                .join(wishlist.member, member)
                .join(wishlist.item, item)
                .where(
                        isItemIdListEq(itemIdList),
                        isMemberIdEq(memberId)
                ).fetch();
    }

    @Override
    public Integer findTotalSalePrice(List<Long> itemIdList, Long memberId) {
        return queryFactory
                .select(item.salePrice.multiply(wishlist.selectedQuantity)
                        .sum()
                )
                .from(wishlist)
                .join(wishlist.item, item).on(item.id.eq(wishlist.item.id))
                .where(wishlist.member.id.eq(memberId)
                        .and(wishlist.item.id.in(itemIdList)))
                .fetchOne();
    }

    @Override
    public Integer findTotalDeliveryPriceForCheckout(List<Long> itemIdList, Long memberId) {
        List<Integer> totalPrice = queryFactory
                .select(item.deliveryPrice)
                .from(wishlist)
                .join(wishlist.item, item).on(item.id.eq(wishlist.item.id))
                .join(item.vendor, vendor).on(vendor.id.eq(item.vendor.id))
                .join(vendor.vendorLicense, vendorLicense).on(vendorLicense.vendor.id.eq(vendor.id))
                .where(wishlist.member.id.eq(memberId).and(wishlist.item.id.in(itemIdList)))
                .groupBy(vendorLicense.id)
                .fetch();

        return totalPrice.stream().mapToInt(Integer::intValue)
                .sum();
    }

    private BooleanExpression isItemIdListEq(List<Long> itemIdList) {
        return itemIdList.isEmpty() ? null : wishlist.item.id.in(itemIdList);
    }

    private BooleanExpression isMemberIdEq(Long memberId) {
        return memberId == null ? null : wishlist.member.id.eq(memberId);
    }
}
