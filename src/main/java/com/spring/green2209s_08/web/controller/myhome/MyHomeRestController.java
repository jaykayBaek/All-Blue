package com.spring.green2209s_08.web.controller.myhome;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.service.AddressService;
import com.spring.green2209s_08.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
@Slf4j
public class MyHomeRestController {

    private final MemberService memberService;
    private final AddressService addressService;

    @PostMapping("/address")
    public ResponseEntity<StatusResponse> addressAdd(@ModelAttribute AddressRequest addressRequest, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);

        addressService.addAddress(memberId, addressRequest);

        StatusResponse response = new StatusResponse(
                HttpStatus.OK.toString(), "주소 등록 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/address")
    public ResponseEntity<StatusResponse> addressEdit(@ModelAttribute AddressRequest addressRequest,
                                                      @RequestParam Long addressId,
                                                      HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);

        addressService.updateAddress(memberId, addressId, addressRequest);

        StatusResponse response = new StatusResponse(
                HttpStatus.OK.toString(), "주소 수정 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
