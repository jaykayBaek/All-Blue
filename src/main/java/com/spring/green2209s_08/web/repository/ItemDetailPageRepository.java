package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.search.ItemRequestDto;

public interface ItemDetailPageRepository {
    ItemRequestDto findItemDetail(Long itemId);
}
