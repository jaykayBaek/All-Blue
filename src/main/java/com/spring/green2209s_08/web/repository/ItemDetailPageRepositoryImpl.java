package com.spring.green2209s_08.web.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.green2209s_08.web.controller.search.ItemRequestDto;
import com.spring.green2209s_08.web.domain.QItem;

import javax.persistence.EntityManager;

import static com.spring.green2209s_08.web.domain.QItem.*;

public class ItemDetailPageRepositoryImpl implements ItemDetailPageRepository{
    private JPAQueryFactory jpaQueryFactory;

    public ItemDetailPageRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public ItemRequestDto findItemDetail(Long itemId) {
        return null;
    }
}
