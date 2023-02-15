package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.ItemImage;
import com.spring.green2209s_08.web.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long enrollProduct(Item item) {
        List<ItemImage> itemImages = item.getItemImages();
        return itemRepository.save(item);
    }

}
