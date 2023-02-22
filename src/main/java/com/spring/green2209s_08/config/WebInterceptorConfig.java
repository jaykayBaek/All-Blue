package com.spring.green2209s_08.config;

import com.spring.green2209s_08.interceptors.MemberLoginCheck;
import com.spring.green2209s_08.interceptors.VendorAlreadyLoginCheck;
import com.spring.green2209s_08.interceptors.VendorLoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VendorLoginCheck())
                .order(1)
                .addPathPatterns("/vendor/**", "/item/**", "/management/**")
                .excludePathPatterns("/vendor/login", "/vendor/logout/**")
                .excludePathPatterns("/vendor/verify/**", "/vendor/register/**")
                .excludePathPatterns("/vendor/join/**")
                .excludePathPatterns("/css/**", "/js/**", "/img/**");
        registry.addInterceptor(new VendorAlreadyLoginCheck())
                .order(2)
                .addPathPatterns("/vendor/login")
                .excludePathPatterns("/css/**", "/js/**", "/img/**");
        registry.addInterceptor(new MemberLoginCheck())
                .order(3)
                .addPathPatterns("/home/**", "/inquiry/**", "/member/account/**")
                .excludePathPatterns("/css/**", "/js/**", "/img/**");
    }
}