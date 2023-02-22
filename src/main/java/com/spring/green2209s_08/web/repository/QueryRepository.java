package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.ItemQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QueryRepository extends JpaRepository<ItemQuery, Long> {
    boolean existsByItemIdAndMemberId(Long itemId, Long memberId);
}
