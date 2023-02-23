package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSearchRepository extends JpaRepository<Item, Long>, ComplexItemSearchRepository {
}
