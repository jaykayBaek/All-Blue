package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.domain.Category;
import com.spring.green2209s_08.web.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findCategoryId(String grandchildId) {
        return categoryRepository.findByGrandchildId(grandchildId);
    }
}
