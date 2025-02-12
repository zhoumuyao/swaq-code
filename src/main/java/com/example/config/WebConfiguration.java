package com.example.config;

import com.example.interceptor.AuthorizeInterceptor;
import com.example.interceptor.OnlineUserInterceptor;
import com.example.listener.MySessionListener;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    @Resource
    OnlineUserInterceptor onlineUserInterceptor;

    /**
     * 设置Cookie的SameSite
     */
    @Bean
    public TomcatContextCustomizer sameSiteCookiesConfig() {
        return context -> {
            final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
            cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
            context.setCookieProcessor(cookieProcessor);
        };
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**");
//        registry.addInterceptor(onlineUserInterceptor)
//                .addPathPatterns("/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
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
