package com.spring.green2209s_08.web.controller.vendor.api;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.controller.vendor.dto.ChangePasswordRequest;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/vendor")
public class VendorApiController {
    private final VendorService vendorService;

    @PostMapping("/verify/vendorLoginId")
    public ResponseEntity<StatusResponse> verifyEmail(String vendorLoginId){
        vendorService.validateLoginId(vendorLoginId);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "아이디 검증 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PostMapping("/account/confirm-password")
    public ResponseEntity<VendorInfoChangeStatusResponse> verifyPasswordForChangeInfo(@RequestParam String vendorPassword,
                                                                                      @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){
        vendorService.vendorPasswordCheck(vendorId, vendorPassword);
        Optional<Vendor> findVendor = vendorService.findById(vendorId);


        VendorInfoChangeResponse response = findVendor.map(m -> new VendorInfoChangeResponse(
                vendorId, m.getVendorLoginId(), m.getVendorName(), m.getVendorEmail(), m.getVendorPhoneNo()
        )).get();

        VendorInfoChangeStatusResponse statusResponse = new VendorInfoChangeStatusResponse(
                HttpStatus.OK.toString(), "비밀번호 검증 완료", "TRUE", response

        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PostMapping("/account/change-password")
    public ResponseEntity<StatusResponse> changePassword(@ModelAttribute ChangePasswordRequest passwordRequest,
                                                         @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){
        Vendor findVendor = vendorService.findById(vendorId).get();
        vendorService.matchPasswordForChangePassword(passwordRequest.getPassword(), findVendor.getVendorPassword());

        vendorService.changePassword(vendorId, passwordRequest);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "비밀번호 변경 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PostMapping("/account/confirm/license")
    public ResponseEntity<StatusResponse> confirmLicense(@ModelAttribute LicenseRequest licenseRequest,
                                                         @SessionAttribute(name = SessionConst.VENDOR_ID, required = false) Long vendorId){
        vendorService.addLicense(vendorId, licenseRequest);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "상세 정보 등록 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusResponse);
    }
}
