package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByGrandchildId(String grandchildId);
}
