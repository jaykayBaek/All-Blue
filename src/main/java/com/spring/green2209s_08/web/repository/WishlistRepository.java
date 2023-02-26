package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>, ComplexWishlistRepository {
    @Query("select w " +
            "from Wishlist w " +
            "where w.item.id = :itemId and w.member.id = :memberId")
    Optional<Wishlist> findByItemIdAndMemberId(Long itemId, Long memberId);

    @Query("select count(w) " +
            "from Wishlist w " +
            "where w.member.id = :memberId")
    Long countWishlist(Long memberId);

//    @Query(
//            "select w, i " +
//            "from Wishlist w " +
//            "join fetch Item i on i.id = w.item.id " +
//            "where w.item.id in(:itemIdList) and w.member.id = :memberId"
//    )
//    List<Wishlist> findAllByItemIdAndMemberId(Long itemIdList, Long memberId);

}