package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.controller.search.ItemDto;
import com.spring.green2209s_08.web.controller.search.ItemSearchCond;
import com.spring.green2209s_08.web.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComplexItemSearchRepository {
    Page<ItemDto> findItemsByCond(ItemSearchCond condition, Pageable pageable);

    // test용
    List<ItemDto> findItemsByCond(ItemSearchCond condition);
}
