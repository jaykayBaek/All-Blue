package com.spring.green2209s_08.web.controller.advisor;

import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.exception.EmailSendException;
import com.spring.green2209s_08.web.exception.MemberAddressException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.spring.green2209s_08.web.controller.myhome")
@Slf4j
public class MyHomeControllerAdvisor {
    @ExceptionHandler(MemberAddressException.class)
    public ResponseEntity<StatusResponse> handleEmailSendException(MemberAddressException e){
        log.warn("MemberAddressException occur: ", e);
        StatusResponse statusResponse = new StatusResponse(
                e.getErrorResult().getStatus().toString(), e.getErrorResult().getMessage(), "FALSE"
        );
        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(statusResponse);
    }
}
