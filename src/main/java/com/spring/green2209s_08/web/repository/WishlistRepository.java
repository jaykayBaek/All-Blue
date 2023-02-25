package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>, ComplexWishlistRepository {
    @Query("select w from Wishlist w where w.item.id = :itemId and w.member.id = :memberId")
    Wishlist findByItemIdAndMemberId(Long itemId, Long memberId);

    @Query("select count(w) " +
            "from Wishlist w " +
            "where w.member.id = :memberId")
    Long countWishlist(Long memberId);
}
