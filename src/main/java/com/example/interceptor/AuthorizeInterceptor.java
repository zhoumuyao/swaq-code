package com.example.interceptor;

import com.example.entity.user.AccountUser;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizeInterceptor implements HandlerInterceptor  {

    @Resource
    UserMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // 表示接受任意域名的请求,也可以指定域名
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        // 该字段可选，是个布尔值，表示是否可以携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT,PATCH, DELETE, OPTIONS");
        // 允许前端传递的请求头参数（按需手动添加）
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With, Accept, Authorization, token, Responsetype");
        response.setHeader("Access-Control-Max-Age", "3600");

        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
