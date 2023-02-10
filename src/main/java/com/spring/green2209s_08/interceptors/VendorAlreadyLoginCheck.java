package com.spring.green2209s_08.interceptors;

import com.spring.green2209s_08.interceptors.interceptorAdvisor.VendorAlreadyLoginException;
import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.exception.InterceptorException;
import com.spring.green2209s_08.web.exception.VendorException;
import com.spring.green2209s_08.web.exception.errorResult.InterceptorErrorResult;
import com.spring.green2209s_08.web.exception.errorResult.VendorErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class VendorAlreadyLoginCheck implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if(session.getAttribute(SessionConst.VENDOR_ID) != null){
            throw new VendorAlreadyLoginException(InterceptorErrorResult.ALREADY_LOGIN);
        }

        return true;
    }
}
