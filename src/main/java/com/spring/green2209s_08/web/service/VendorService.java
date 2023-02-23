package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.vendor.api.LicenseRequest;
import com.spring.green2209s_08.web.controller.vendor.dto.ChangePasswordRequest;
import com.spring.green2209s_08.web.controller.vendor.dto.VendorInfoChangeRequest;
import com.spring.green2209s_08.web.domain.StoreAddress;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.domain.VendorLicense;
import com.spring.green2209s_08.web.exception.errorResult.VendorErrorResult;
import com.spring.green2209s_08.web.exception.VendorException;
import com.spring.green2209s_08.web.repository.LicenseRepository;
import com.spring.green2209s_08.web.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class VendorService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;
    private final LicenseRepository licenseRepository;

    public void validateLoginId(String loginId){
        Optional<Vendor> findVendor = vendorRepository.findByVendorLoginId(loginId);
        if(findVendor.isPresent()){
            throw new VendorException(VendorErrorResult.DUPLICATE_OR_LEAVED_LOGIN_ID);
        }
    }

    public void register(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public Optional<Vendor> findById(Long id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        return vendor;
    }

    public Long login(String vendorLoginId, String vendorPassword) {
        Optional<Vendor> vendor = vendorRepository.findByVendorLoginId(vendorLoginId);

        if(vendor.isEmpty()){
            throw new VendorException(VendorErrorResult.LOGIN_FAIL);
        }
        if(!passwordEncoder.matches(vendorPassword, vendor.get().getVendorPassword())){
            throw new VendorException(VendorErrorResult.LOGIN_FAIL);
        }

        return vendor.get().getId();
    }

    public void vendorPasswordCheck(Long vendorId, String vendorPassword) {
        Vendor findVendor = vendorRepository.findById(vendorId).get();
        boolean result = passwordEncoder.matches(vendorPassword, findVendor.getVendorPassword());
        if(result == false){
            throw new VendorException(VendorErrorResult.PASSWORD_NOT_MATCH);
        }
    }

    @Transactional
    public void changeVendorInfo(Long vendorId, VendorInfoChangeRequest changeRequest) {
        Vendor findVendor = vendorRepository.findById(vendorId).get();
        findVendor.changeInfo(changeRequest.getVendorName(), changeRequest.getVendorEmail(), changeRequest.getVendorPhoneNo());
    }

    public void matchPasswordForChangePassword(String password, String vendorPassword) {
        boolean result = passwordEncoder.matches(password, vendorPassword);

        if(result == false){
            throw new VendorException(VendorErrorResult.PASSWORD_NOT_MATCH);
        }
    }

    @Transactional
    public void changePassword(Long vendorId, ChangePasswordRequest passwordRequest) {
        Vendor vendor = findById(vendorId).get();

        if(!passwordRequest.getNewPassword().equals(passwordRequest.getRepeatNewPassword())){
            throw new VendorException(VendorErrorResult.CHANGE_PASSWORD_NOT_MATCH);
        }

        vendor.changePassword(passwordEncoder.encode(passwordRequest.getRepeatNewPassword()));
    }

    public Long addLicense(Long vendorId, LicenseRequest request) {
        Vendor vendor = vendorRepository.findById(vendorId).get();
        StoreAddress address = StoreAddress.builder()
                .zipcode(request.getZipcode())
                .address(request.getAddress())
                .detail(request.getDetail())
                .build();
        VendorLicense license = VendorLicense.builder()
                .storeName(request.getStoreName())
                .licenseNo(request.getLicenseNo())
                .storeAddress(address)
                .vendor(vendor)
                .build();

        return licenseRepository.save(license).getId();
    }
}
