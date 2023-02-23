package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Item;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemDetailPageRepository, ItemViewRepository {

    @Query("select i from Item i where i.id = :itemId")
    Optional<Item> findItem(Long itemId);
}
