package com.spring.green2209s_08.web.member.controller.advisor;

import com.spring.green2209s_08.web.member.controller.StatusResponse;
import com.spring.green2209s_08.web.member.domain.Member;
import com.spring.green2209s_08.web.member.exception.MemberException;
import com.spring.green2209s_08.web.member.exception.RedisDataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("com.spring.green2209s_08.web.member.controller")
public class MemberControllerAdvisor {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<StatusResponse> handleMemberException(MemberException e){
        log.warn("MemberException occur: ", e);
        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.BAD_REQUEST.toString(), e.getErrorResult().getMessage(), "FALSE"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(statusResponse);
    }

//    @ExceptionHandler(RedisDataNotFoundException.class)
//    public ResponseEntity<StatusResponse> handleRedisDataException(RedisDataNotFoundException e){
//        log.warn("RedisDataNotFoundException occur : ", e);
//        StatusResponse statusResponse = new StatusResponse(
//                HttpStatus.BAD_REQUEST.toString(), e.getMessage(), "FALSE"
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(statusResponse);
//    }

}
