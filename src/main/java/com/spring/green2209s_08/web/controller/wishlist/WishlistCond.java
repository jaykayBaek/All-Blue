package com.spring.green2209s_08.web.controller.wishlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WishlistCond {
    private Long memberId;
    private List<Long> itemIdList;
    private List<Integer> quantityList;

    public WishlistCond(Long memberId, List<Long> itemIdList) {
        this(memberId, itemIdList, null);
    }
}
