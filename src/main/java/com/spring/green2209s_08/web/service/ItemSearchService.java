package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.search.ItemDto;
import com.spring.green2209s_08.web.controller.search.ItemSearchCond;
import com.spring.green2209s_08.web.repository.ItemSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemSearchService {
    private final ItemSearchRepository itemSearchRepository;

    public Page<ItemDto> findItemsByCond(ItemSearchCond condition, Pageable pageable) {
        return itemSearchRepository.findItemsByCond(condition, pageable);
    }

    public List<ItemDto> findItemsByCond(ItemSearchCond condition) {
        return itemSearchRepository.findItemsByCond(condition);
    }



}
