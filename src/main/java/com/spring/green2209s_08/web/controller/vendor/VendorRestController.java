package com.spring.green2209s_08.web.controller.vendor;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.controller.vendor.dto.ChangePasswordRequest;
import com.spring.green2209s_08.web.controller.vendor.dto.VendorLoginRequest;
import com.spring.green2209s_08.web.controller.vendor.dto.VendorRegisterRequest;
import com.spring.green2209s_08.web.domain.Vendor;
import com.spring.green2209s_08.web.domain.enums.AccountType;
import com.spring.green2209s_08.web.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/vendor")
public class VendorRestController {
    private final VendorService vendorService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<StatusResponse> register(@Validated VendorRegisterRequest request, BindingResult bindingResult){
        vendorService.validateLoginId(request.getVendorLoginId());

        request.changeEncodedPassword(passwordEncoder.encode(request.getVendorPassword()));
        Vendor vendor = createVendor(request);

        vendorService.register(vendor);
        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.CREATED.toString(), "판매자 회원가입 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusResponse);
    }

    private static Vendor createVendor(VendorRegisterRequest request) {
        Vendor vendor = Vendor.builder()
                .vendorLoginId(request.getVendorLoginId())
                .vendorPassword(request.getVendorPassword())
                .vendorName(request.getVendorName())
                .vendorEmail(request.getVendorEmail())
                .vendorPhoneNo(request.getVendorPhoneNo())
                .accountType(AccountType.VENDOR)
                .build();
        return vendor;
    }

    @PostMapping("/login")
    public ResponseEntity<StatusResponse> login(
            @Validated VendorLoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request) {
        Long vendorId = vendorService.login(loginRequest.getVendorLoginId(), loginRequest.getVendorPassword());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.VENDOR_ID, vendorId);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "로그인 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<StatusResponse> logout(){
        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "로그아웃 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }

    @PostMapping("/account/change-password")
    public ResponseEntity<StatusResponse> changePassword(@ModelAttribute ChangePasswordRequest passwordRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long vendorId = (Long) session.getAttribute(SessionConst.VENDOR_ID);

        Vendor findVendor = vendorService.findById(vendorId).get();
        vendorService.matchPasswordForChangePassword(passwordRequest.getPassword(), passwordRequest.getNewPassword(), findVendor.getVendorPassword());

        vendorService.changePassword(vendorId, passwordRequest);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.OK.toString(), "비밀번호 변경 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(statusResponse);
    }
    
}