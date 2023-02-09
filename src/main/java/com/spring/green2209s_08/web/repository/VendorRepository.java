package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByVendorLoginId(String loginId);
}
