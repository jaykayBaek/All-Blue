package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.VendorLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<VendorLicense, Long> {

    boolean existsLicenseByVendorId(Long vendorId);
}
