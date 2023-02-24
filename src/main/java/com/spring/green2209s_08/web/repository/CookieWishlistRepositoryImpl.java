package com.spring.green2209s_08.web.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.wishlist.WishlistCond;
import com.spring.green2209s_08.web.controller.wishlist.WishlistViewDto;
import com.spring.green2209s_08.web.domain.QItem;

import javax.persistence.EntityManager;
import java.util.List;

import static com.spring.green2209s_08.web.domain.QItem.*;
import static com.spring.green2209s_08.web.domain.QItemImage.itemImage;
import static com.spring.green2209s_08.web.domain.QVendor.vendor;
import static com.spring.green2209s_08.web.domain.QVendorLicense.vendorLicense;
import static com.spring.green2209s_08.web.domain.QWishlist.wishlist;
import static org.springframework.util.CollectionUtils.isEmpty;

public class CookieWishlistRepositoryImpl implements CookieWishlistRepository{

    private JPAQueryFactory queryFactory;

    public CookieWishlistRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<WishlistViewDto> findWishlist(WishlistCond condition) {
        return queryFactory
                .select(
                        Projections.fields(WishlistViewDto.class,
                                item.id.as("itemId"),
                                itemImage.savedImageName,
                                item.itemName,
                                item.salePrice,
                                item.deliveryPrice,
                                item.stockQuantity,
                                vendorLicense.id.as("vendorLicenseId"),
                                vendorLicense.storeName
                        )
                )
                .from(item)
                .leftJoin(item.itemImages, itemImage)
                    .on(
                            itemImage.item.id
                                    .eq(item.id)
                                    .and(itemImage.thumbnailImage.isTrue())
                    )
                .leftJoin(item.vendor, vendor)
                .leftJoin(vendor.vendorLicense, vendorLicense)
                .where(
//                        isLogin(condition.getMemberId()),
                        isHadItemIdCookie(condition.getItemIdList())
                )
                .fetch();
    }

    private BooleanExpression isLogin(Long memberId) {
        return memberId == null ? null : wishlist.member.id.eq(memberId);
    }

    private BooleanExpression isHadItemIdCookie(List<Long> itemIdList) {
        return isEmpty(itemIdList) ? null : item.id.in(itemIdList);
    }
}
