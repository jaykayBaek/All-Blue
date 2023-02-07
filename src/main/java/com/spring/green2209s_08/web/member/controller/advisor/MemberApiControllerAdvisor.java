package com.spring.green2209s_08.web.member.controller.advisor;

import com.spring.green2209s_08.web.member.controller.StatusResponse;
import com.spring.green2209s_08.web.member.exception.EmailSendException;
import com.spring.green2209s_08.web.member.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.spring.green2209s_08.web.member.controller")
public class MemberApiControllerAdvisor {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<StatusResponse> handleMemberException(MemberException e){
        log.warn("MemberException occur: ", e);
        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.BAD_REQUEST.toString(), e.getErrorResult().getMessage(), "FALSE"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(statusResponse);
    }
}
