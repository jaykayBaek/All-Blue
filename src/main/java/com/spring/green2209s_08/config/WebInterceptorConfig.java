package com.spring.green2209s_08.config;

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
                .addPathPatterns("/vendor/**", "/item/**")
                .excludePathPatterns("/vendor/login/**")
                .excludePathPatterns("/vendor/logout/**")
                .excludePathPatterns("/vendor/verify/**")
                .excludePathPatterns("/vendor/register/**")
                .excludePathPatterns("/vendor/join/**");
        registry.addInterceptor(new VendorAlreadyLoginCheck())
                .order(2)
                .addPathPatterns("/vendor/login");
    }
}
