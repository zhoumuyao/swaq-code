package com.example.service;

import java.time.Duration;

public interface OnlineUserStatsService {

    /**
     * 添加用户在线信息
     * @param username
     * @return
     */
    Boolean online(String username);

    /**
     * 获取一定时间内，在线的用户数量
     * @param duration
     * @return
     */
    Long count(Duration duration);

    /**
     * 获取所有在线过的用户数量，不论时间
     * @return
     */
    Long count();

    /**
     * 清除超过一定时间没在线的用户数据
     * @param duration
     * @return
     */
    Long clear(Duration duration);


    Long clear(String username);
}
