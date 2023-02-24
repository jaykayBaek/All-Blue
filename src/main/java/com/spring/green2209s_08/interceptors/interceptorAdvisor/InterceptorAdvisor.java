package com.spring.green2209s_08.interceptors.interceptorAdvisor;

import com.spring.green2209s_08.interceptors.RedirectUrlConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.exception.MemberInterceptorException;
import com.spring.green2209s_08.web.exception.VendorInterceptorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RestControllerAdvice
public class InterceptorAdvisor {

    @ExceptionHandler(VendorInterceptorException.class)
    public ResponseEntity<StatusResponse> vendorExceptionHandler(VendorInterceptorException e,
                                                                      HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.warn("InterceptorException occur: ", e);

        StatusResponse statusResponse = new StatusResponse(
                e.getErrorResult().getStatus().toString(), e.getErrorResult().getMessage(), "FALSE"
        );

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('비정상적인 접근이거나, 세션이 만료되었습니다. 권한 인증을 위해 다시 로그인해주세요.'); location.href='"+ RedirectUrlConst.CONTEXT_PATH +"/vendor/login';</script>");
        out.flush();

        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(statusResponse);
    }
    @ExceptionHandler(VendorAlreadyLoginException.class)
    public ResponseEntity<StatusResponse> vendorAlreayLoginHandler(VendorAlreadyLoginException e,
                                                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.warn("VendorException occur: ", e);

        StatusResponse statusResponse = new StatusResponse(
                e.getErrorResult().getStatus().toString(), e.getErrorResult().getMessage(), "FALSE"
        );

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('비정상적인 접근입니다.'); location.href='"+ RedirectUrlConst.CONTEXT_PATH +"/';</script>");
        out.flush();

        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(statusResponse);
    }

    @ExceptionHandler(MemberInterceptorException.class)
    public ResponseEntity<StatusResponse> vendorExceptionHandler(MemberInterceptorException e,
                                                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.warn("MemberInterceptorException occur: ", e);

        StatusResponse statusResponse = new StatusResponse(
                e.getErrorResult().getStatus().toString(), e.getErrorResult().getMessage(), "FALSE"
        );

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('비정상적인 접근이거나, 세션이 만료되었습니다. 권한 인증을 위해 다시 로그인해주세요.'); location.replace('/green2209s_08/');</script>");
        out.flush();

        return ResponseEntity.status(e.getErrorResult().getStatus())
                .body(statusResponse);
    }

}
