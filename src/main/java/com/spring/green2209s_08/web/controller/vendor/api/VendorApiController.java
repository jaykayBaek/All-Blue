package com.spring.green2209s_08.web.controller.vendor.api;

import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.VendorService;
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("vendor/")
public class VendorApiController {
    private final VendorService vendorService;

    @PostMapping("verify/vendorLoginId")
    public ResponseEntity<StatusResponse> verifyEmail(String vendorLoginId){
        vendorService.validateLoginId(vendorLoginId);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "아이디 검증 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

}
