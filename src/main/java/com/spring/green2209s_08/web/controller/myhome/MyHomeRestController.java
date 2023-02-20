package com.spring.green2209s_08.web.controller.myhome;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.service.AddressService;
import com.spring.green2209s_08.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class MyHomeRestController {
    private final MemberService memberService;
    private final AddressService addressService;
    @PostMapping("/address")
    public ResponseEntity<StatusResponse> addressAdd(@ModelAttribute AddressRequest addressRequest, HttpServletRequest request){
        Long memberId = (Long) request.getAttribute(SessionConst.MEMBER_ID);

        addressService.addAddress(memberId, addressRequest);

        StatusResponse response = new StatusResponse(
                HttpStatus.OK.toString(), "주소 등록 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
