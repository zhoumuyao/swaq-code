package com.example.service;

import com.example.entity.DangerInfo;
import com.example.entity.vo.DangerVo;

public interface DangerInfoService {
    DangerInfo queryDanger(int i);

    String updateDanger(DangerVo dangerVo);
}
