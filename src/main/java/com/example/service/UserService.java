package com.example.service;

import com.example.entity.user.AccountUser;

public interface UserService {
    AccountUser searchInfo(String username);
}
