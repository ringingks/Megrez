package com.Y3.AnalyticsTeam.CT.Cancer;

import com.Y3.AnalyticsTeam.CT.Cancer.service.IRedisService;
import com.Y3.AnalyticsTeam.CT.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class Redistest {

    @Autowired
    private IRedisService service;
    @Autowired
    private RedisUtil util;

    @Scheduled(initialDelayString="${jobs.initialDelay}", fixedRateString="${jobs.fixedRate}")
    public void test() {
        String key = "key_"+System.currentTimeMillis();
        String value = "val_"+System.currentTimeMillis();
//        service.put(key,value,-1);

        util.setCacheObject(key,value);

        util.getCacheSet()

        Set<String> keys = service.getKeys();
        List li = service.getAll();
        for(String k : keys){
            System.out.println(k);
        }
    }
}
