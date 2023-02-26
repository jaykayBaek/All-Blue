package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.domain.VendorLicense;
import com.spring.green2209s_08.web.repository.VendorLicenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VendorLicenseService {

    private final VendorLicenseRepository vendorLicenseRepository;

    public Optional<VendorLicense> findByVendorId(Long vendorId) {
        return vendorLicenseRepository.findByVendorId(vendorId);
    }
}
