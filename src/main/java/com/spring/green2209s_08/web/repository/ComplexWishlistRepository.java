package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.wishlist.WishlistCond;
import com.spring.green2209s_08.web.controller.wishlist.WishlistViewDto;

import java.util.List;

public interface ComplexWishlistRepository {
    List<WishlistViewDto> findWishlist(WishlistCond condition);
}
