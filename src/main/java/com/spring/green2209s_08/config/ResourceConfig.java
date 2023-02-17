package com.spring.green2209s_08.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    private String boardPath = "/images/board/**";
    private String extraPath = "/images/extra/**";
    private String thumbnailPath = "/images/thumbnail/**";
    private String boardResourcePath = "file:///D:/green2209s-08-files/images/board-image/save/";
    private String extraResourcePath = "file:///D:/green2209s-08-files/images/extra-image/";
    private String thumbnailResourcePath = "file:///D:/green2209s-08-files/images/thumbnail-image/";
//    private String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(boardPath)
                .addResourceLocations(boardResourcePath);
        registry.addResourceHandler(extraPath)
                .addResourceLocations(extraResourcePath);
        registry.addResourceHandler(thumbnailPath)
                .addResourceLocations(thumbnailResourcePath);
    }
}
