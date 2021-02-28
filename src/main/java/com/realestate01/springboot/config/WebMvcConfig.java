package com.realestate01.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/summernoteImage/**") //리소스 업로드 요청 시
                .addResourceLocations("file:///C:/summernote_image/");
        registry.addResourceHandler("/upload/property_image/**") //리소스 업로드 요청 시
                .addResourceLocations("file:///c:/upload/property_image/");
    }
}