package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Stack;

public interface AuthorizeService  extends UserDetailsService {
    boolean sendValidateEmail(String email,String sessionId);
}
