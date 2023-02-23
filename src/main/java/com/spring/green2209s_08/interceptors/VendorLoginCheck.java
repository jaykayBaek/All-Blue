package com.spring.green2209s_08.interceptors;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.exception.VendorInterceptorException;
import com.spring.green2209s_08.web.exception.errorResult.InterceptorErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
public class VendorLoginCheck implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        Optional<Long> vendorId = Optional.ofNullable((Long)session.getAttribute(SessionConst.VENDOR_ID));
        if(vendorId.isEmpty()){
            throw new VendorInterceptorException(InterceptorErrorResult.UNAUTHORIZED);
        }

        return true;
    }
}
