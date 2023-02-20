package com.spring.green2209s_08.web.controller.advisor;

import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.exception.EmailSendException;
import com.spring.green2209s_08.web.exception.VendorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ControllerAdvice("com.spring.green2209s_08.web.controller.vendor")
public class VendorRestControllerAdvisor {

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
