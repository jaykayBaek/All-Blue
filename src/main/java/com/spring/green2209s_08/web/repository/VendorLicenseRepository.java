package com.spring.green2209s_08.web.repository;

import com.spring.green2209s_08.web.domain.VendorLicense;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VendorLicenseRepository extends JpaRepository<VendorLicense, Long> {
    @BatchSize(size = 1000)
    Optional<VendorLicense> findByVendorId(Long vendorId);

    @Query("select l from VendorLicense l " +
            "join Vendor v on v.id = l.vendor.id " +
            "join Item i on i.vendor.id = v.id " +
            "where i.id = :itemId")
    VendorLicense findByItemId(Long itemId);
}
