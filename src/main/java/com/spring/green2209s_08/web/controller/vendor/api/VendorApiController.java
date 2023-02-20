package com.spring.green2209s_08.web.controller.vendor.api;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("vendor/")
public class VendorApiController {
    private final VendorService vendorService;

    @PostMapping("verify/vendorLoginId")
    public ResponseEntity<StatusResponse> verifyEmail(String vendorLoginId){
        log.info("vendorLoginId? {}", vendorLoginId);
        vendorService.validateLoginId(vendorLoginId);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "아이디 검증 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PostMapping("account/confirm-password")
    public ResponseEntity<VendorInfoChangeStatusResponse> verifyPasswordForChangeInfo(
            @RequestParam String vendorPassword, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

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

}
