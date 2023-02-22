package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.ItemQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<ItemQuery, Long> {
}
