package com.spring.green2209s_08.web.controller.advisor;

import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.exception.ItemException;
import com.spring.green2209s_08.web.exception.MemberAddressException;
import com.spring.green2209s_08.web.exception.MemberException;
import com.spring.green2209s_08.web.exception.VendorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("com.spring.green2209s_08.web.controller")
public class ControllerAdvisor {
    @ExceptionHandler(ItemException.class)
    public ResponseEntity<StatusResponse> handleMemberException(ItemException e){
        log.warn("ItemException occur: ", e);
        StatusResponse statusResponse = new StatusResponse(
                e.getErrorResult().getStatus().toString(), e.getErrorResult().getMessage(), "FALSE"
        );
        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(statusResponse);
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<StatusResponse> handleMemberException(MemberException e){
        log.warn("MemberException occur: ", e);
        StatusResponse statusResponse = new StatusResponse(
                e.getErrorResult().getStatus().toString(), e.getErrorResult().getMessage(), "FALSE"
        );
        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(statusResponse);
    }

    @ExceptionHandler(MemberAddressException.class)
    public ResponseEntity<StatusResponse> handleEmailSendException(MemberAddressException e){
        log.warn("MemberAddressException occur: ", e);
        StatusResponse statusResponse = new StatusResponse(
                e.getErrorResult().getStatus().toString(), e.getErrorResult().getMessage(), "FALSE"
        );
        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(statusResponse);
    }
    @ExceptionHandler(VendorException.class)
    public ResponseEntity<StatusResponse> handleVendorException(VendorException e){
        log.warn("VendorException occur", e);
        StatusResponse statusResponse = new StatusResponse(
                e.getErrorResult().getStatus().toString(), e.getErrorResult().getMessage(), "FALSE"
        );
        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(statusResponse);
    }


}
