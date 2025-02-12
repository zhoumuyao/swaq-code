package com.example.service.Impl;

import com.example.service.InvestService;
import com.example.service.OnlineUserStatsService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class OnlineUserStatsServiceImpl implements OnlineUserStatsService {

    private static final String ONLINE_USERS = "onlie_users";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Boolean online(String username) {
        return stringRedisTemplate.opsForZSet().add(ONLINE_USERS, username, Instant.now().toEpochMilli());
    }

    @Override
    public Long count(Duration duration) {
        LocalDateTime now = LocalDateTime.now();
        return stringRedisTemplate.opsForZSet().count(ONLINE_USERS,
                now.minus(duration).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    @Override
    public Long count() {
        return stringRedisTemplate.opsForZSet().zCard(ONLINE_USERS);
    }

    @Override
    public Long clear(Duration duration) {
        return stringRedisTemplate.opsForZSet().removeRangeByScore(ONLINE_USERS, 0,
                LocalDateTime.now().minus(duration).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    @Override
    public Long clear(String username) {
        return stringRedisTemplate.opsForZSet().remove(ONLINE_USERS, username);
    }
}
