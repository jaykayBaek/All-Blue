package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
