package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.VendorLicense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorLicenseRepository extends JpaRepository<VendorLicense, Long> {
    Optional<VendorLicense> findByVendorId(Long vendorId);
}
