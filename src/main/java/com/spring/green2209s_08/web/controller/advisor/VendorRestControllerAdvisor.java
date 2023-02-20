package com.spring.green2209s_08.web.controller.advisor;

import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.exception.EmailSendException;
import com.spring.green2209s_08.web.exception.VendorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.spring.green2209s_08.web.controller.vendor")
public class VendorRestControllerAdvisor {

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<StatusResponse> handleEmailSendException(EmailSendException e){
        log.warn("EmailSendException occur: ", e);
        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getErrorResult().getMessage(), "FALSE"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
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
