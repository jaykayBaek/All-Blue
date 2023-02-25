package com.spring.green2209s_08.web.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.wishlist.WishlistCond;
import com.spring.green2209s_08.web.controller.wishlist.WishlistViewDto;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;

import static com.spring.green2209s_08.web.domain.QItem.item;
import static com.spring.green2209s_08.web.domain.QItemImage.itemImage;
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
                        isLogin(condition.getMemberId())
                )
                .fetch();
    }


    private BooleanExpression isHadItemIdCookie(List<Long> itemIdList) {
        return isEmpty(itemIdList) ? null : wishlist.item.id.in(itemIdList);
    }


    private BooleanExpression isLogin(Long memberId) {
        return memberId == null ? null : wishlist.member.id.eq(memberId);
    }
}
