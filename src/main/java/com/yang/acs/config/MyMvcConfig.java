package com.yang.acs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /* registry.addViewController("/").setViewName("index");*/

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirPath = System.getProperty("user.home");
//        System.out.println("debug===============================>" + dirPath);
        registry.addResourceHandler("/acs/faceImg/**").addResourceLocations("file:" + dirPath + File.separator
                + "acs"+File.separator + "faceImg" + File.separator);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/login", "/dolog", "/css/**", "/fonts/**", "/images/**", "/js/**");
    }
}
