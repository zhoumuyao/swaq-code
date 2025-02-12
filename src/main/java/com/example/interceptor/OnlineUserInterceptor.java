package com.example.interceptor;

import com.example.service.OnlineUserStatsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class OnlineUserInterceptor implements HandlerInterceptor {

    @Resource
    OnlineUserStatsService onlineUserStatsService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String username =(String) request.getSession().getAttribute("name");
        System.out.println("SessionId:" + request.getSession().getId());
        onlineUserStatsService.online("admin");
        return true;
    }
}
