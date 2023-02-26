package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.wishlist.WishlistCond;
import com.spring.green2209s_08.web.controller.wishlist.WishlistViewDto;
import com.spring.green2209s_08.web.domain.Wishlist;

import java.util.List;

public interface ComplexWishlistRepository {
    List<WishlistViewDto> findWishlist(WishlistCond condition);

    List<Wishlist> findAllByItemIdAndMemberId(List<Long> itemIdList, Long memberId);

    Integer findTotalSalePrice(List<Long> itemIdList, Long memberId);

    Integer findTotalDeliveryPriceForCheckout(List<Long> itemIdList, Long memberId);
}
