package com.example.config;

import com.example.interceptor.AuthorizeInterceptor;
import com.example.listener.MySessionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${IMAGE.UPLOAD.PATH}")
    private String uploadPath;
    @Resource
    AuthorizeInterceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/info/**")
                .addResourceLocations(uploadPath);
    }

    @Bean
    public ServletListenerRegistrationBean<MySessionListener> mySessionListener() {
        ServletListenerRegistrationBean<MySessionListener> registrationBean
                = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new MySessionListener());
        return registrationBean;
    }
}
