package com.example.interceptor;

import com.example.entity.user.AccountUser;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Resource
    UserMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        String username = user.getUsername();
//        AccountUser account = mapper.findAccountUserByNameOrEmail(username);
//        request.getSession().setAttribute("account",account);
        response.setHeader("Access-Control-Expose-Headers", "token");// 服务器 headers 白名单，可以让客户端进行访问操作的属性
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        return true;
    }
}
