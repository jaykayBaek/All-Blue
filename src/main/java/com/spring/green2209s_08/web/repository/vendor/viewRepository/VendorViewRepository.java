package com.spring.green2209s_08.web.repository.vendor.viewRepository;

import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.domain.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VendorViewRepository extends JpaRepository<Vendor, Long> {
    
    @Query("select i from Vendor v join v.items i on i.vendor.id = :vendorId")
    Page<Item> findUploadItemList(@Param("vendorId") Long vendorId, Pageable pageable);
}
