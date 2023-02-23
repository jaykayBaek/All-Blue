package com.spring.green2209s_08.interceptors;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.exception.MemberInterceptorException;
import com.spring.green2209s_08.web.exception.errorResult.InterceptorErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminLoginCheck implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);
        if(memberId == null || memberId != 1){
            throw new MemberInterceptorException(InterceptorErrorResult.UNAUTHORIZED);
        }

        return true;
    }
}
