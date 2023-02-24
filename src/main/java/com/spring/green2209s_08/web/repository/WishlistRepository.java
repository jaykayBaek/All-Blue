package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>, ComplexWishlistRepository {
}
