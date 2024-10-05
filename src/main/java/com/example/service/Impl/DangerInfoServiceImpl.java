package com.example.service.Impl;

import com.example.entity.DangerInfo;
import com.example.mapper.DangerInfoMapper;
import com.example.service.DangerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DangerInfoServiceImpl implements DangerInfoService {
    @Resource
    DangerInfoMapper dangerInfoMapper;
    @Override
    public DangerInfo queryDanger(int i) {
        return dangerInfoMapper.queryDangerInfo(i);
    }
}
