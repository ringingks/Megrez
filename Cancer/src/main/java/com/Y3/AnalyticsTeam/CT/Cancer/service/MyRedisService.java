package com.Y3.AnalyticsTeam.CT.Cancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyRedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void setString(String key, String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public String getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

}
