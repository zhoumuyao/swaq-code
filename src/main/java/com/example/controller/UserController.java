package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.user.AccountUser;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/me")
    public RestBean<AccountUser> me(@SessionAttribute("account")AccountUser accountUser){
        return RestBean.success(accountUser);
    }

    @PostMapping("/searchInfo")
    public RestBean<AccountUser> searchInfo(@RequestParam("username") String username){
        AccountUser accountUser = userService.searchInfo(username);
        return RestBean.success(accountUser);
    }

}
