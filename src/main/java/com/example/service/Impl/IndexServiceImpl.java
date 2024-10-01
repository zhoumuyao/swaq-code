package com.example.service.Impl;

import com.example.mapper.UserMapper;
import com.example.service.FeedbackService;
import com.example.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    UserMapper userMapper;

    @Override
    public int getOnlineNumber() {
        return userMapper.getOnlineNumber();
    }
}
