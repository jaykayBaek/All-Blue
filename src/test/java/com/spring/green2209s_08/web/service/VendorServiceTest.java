package com.spring.green2209s_08.web.service;

import com.spring.green2209s_08.web.controller.vendor.api.LicenseRequest;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.exception.errorResult.VendorErrorResult;
import com.spring.green2209s_08.web.exception.VendorException;
import com.spring.green2209s_08.web.repository.LicenseRepository;
import com.spring.green2209s_08.web.repository.VendorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class VendorServiceTest {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LicenseRepository licenseRepository;

    @Test
    void 회원가입() {
        //given
        Vendor vendor = Vendor.builder()
                .vendorLoginId("test").build();
        vendorService.register(vendor);
        //when
        Optional<Vendor> target = vendorService.findById(vendor.getId());

        //then
        assertThat(target.isEmpty()).isFalse();

    }
    @Test
    void 이미존재하는_아이디_벤더() {
        //given
        Vendor vendor = Vendor.builder()
                .vendorLoginId("test").build();
        vendorService.register(vendor);

        Vendor target = Vendor.builder()
                .vendorLoginId("test")
                .vendorName("홍길동")
                .build();

        //when
        VendorException e = assertThrows(VendorException.class, () ->
                vendorService.validateLoginId(target.getVendorLoginId())
        );

        //then
        assertThat(e.getErrorResult()).isEqualTo(VendorErrorResult.DUPLICATE_OR_LEAVED_LOGIN_ID);
    }

    @Test
    void 판매자정보변경_비밀번호확인() {
        //given
        Vendor target = Vendor.builder()
                .vendorLoginId("test")
                .vendorPassword(passwordEncoder.encode("1234"))
                .vendorName("홍길동")
                .build();
        vendorService.register(target);

        //when
        VendorException e = assertThrows(VendorException.class, () ->
                vendorService.vendorPasswordCheck(target.getId(), "12345")
        );

        //then
        assertThat(e.getErrorResult().getMessage()).isEqualTo(VendorErrorResult.PASSWORD_NOT_MATCH.getMessage());
    }

    @Test
    void 상세정보등록() {
        //given
        Vendor tester = Vendor.builder()
                .vendorName("tester")
                .build();
        vendorService.register(tester);
        LicenseRequest request = LicenseRequest.builder()
                .address("test")
                .zipcode("1")
                .licenseNo("1")
                .storeName("1")
                .detail("1")
                .build();
        //when
        Long saveDetails = vendorService.addLicense(tester.getId(), request);

        //then
        assertThat(saveDetails).isEqualTo(1L);
    }
    
    @Test
    void 상세정보등록했는지확인() {
        //given
        Vendor tester1 = Vendor.builder()
                .vendorName("tester1")
                .build();
        Vendor tester2 = Vendor.builder()
                .vendorName("tester2")
                .build();
        vendorService.register(tester1);
        vendorService.register(tester2);

        LicenseRequest request = LicenseRequest.builder()
                .address("test")
                .zipcode("1")
                .licenseNo("1")
                .storeName("1")
                .detail("1")
                .build();
        vendorService.addLicense(tester1.getId(), request);

        //when
        boolean result1 = vendorService.isLicenseVendor(tester1.getId());
        boolean result2 = vendorService.isLicenseVendor(tester2.getId());

        //then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();

    }

}