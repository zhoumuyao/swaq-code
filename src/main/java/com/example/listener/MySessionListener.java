package com.example.listener;

import com.example.entity.user.AccountUser;
import com.example.service.AuthorizeService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;
@WebListener
public class MySessionListener implements HttpSessionListener {

    private AuthorizeService authorizeService;


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Session创建时的逻辑
        System.out.println("Session Created: " + se.getSession().getAttribute("name"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session销毁时的逻辑
        System.out.println("Session Destroyed: " + se.getSession().getAttribute("name"));
        // 从在线用户列表中移除用户
        if (se.getSession().getAttribute("name") != null){
            Object name = se.getSession().getAttribute("name");
            ServletContext sc = se.getSession().getServletContext();
            ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
            AuthorizeService authorizeService = applicationContext.getBean(AuthorizeService.class);
            // 检查authorizeService是否为null
            authorizeService.logout(name.toString());
        }

    }

}