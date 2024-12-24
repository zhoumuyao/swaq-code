package com.example.service.Impl;

import com.example.entity.auth.Account;
import com.example.entity.user.AccountUser;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public AccountUser searchInfo(String username) {
        if(username == null)
            throw new UsernameNotFoundException("用户名不能为空");
        AccountUser accountUser = userMapper.findAccountUserByNameOrEmailOrPoliceId(username);
        if (accountUser == null)
            throw new UsernameNotFoundException("找不到该用户");
        return accountUser;
    }
}
