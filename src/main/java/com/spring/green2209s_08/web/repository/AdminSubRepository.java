package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AdminSubRepository {
    private final EntityManager em;
    public void saveCategory(Category category){
        em.persist(category);
    }
}
