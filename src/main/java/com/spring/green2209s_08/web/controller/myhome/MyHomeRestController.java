package com.spring.green2209s_08.web.controller.myhome;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.service.AddressService;
import com.spring.green2209s_08.web.service.MemberService;
import com.spring.green2209s_08.web.service.ReviewService;
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

    private final AddressService addressService;
    private final ReviewService reviewService;

    @PostMapping("/address")
    public ResponseEntity<StatusResponse> addressAdd(@ModelAttribute AddressRequest addressRequest,
                                                     @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId){
        addressService.addAddress(memberId, addressRequest);

        StatusResponse response = new StatusResponse(
                HttpStatus.OK.toString(), "주소 등록 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/address")
    public ResponseEntity<StatusResponse> addressEdit(@ModelAttribute AddressRequest addressRequest,  @RequestParam Long addressId,
                                                      @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId){
        addressService.updateAddress(memberId, addressId, addressRequest);

        StatusResponse response = new StatusResponse(
                HttpStatus.OK.toString(), "주소 수정 완료", "TRUE"
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/review/write")
    public ResponseEntity<StatusResponse> reviewWrite(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) Long memberId,
                                                      @ModelAttribute ReviewRequest request){

        reviewService.writeReview(memberId, request);

        StatusResponse response = new StatusResponse(
                HttpStatus.CREATED.toString(), "리뷰 작성 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
