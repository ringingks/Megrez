package com.Y3.AnalyticsTeam.CT.Cancer;

import com.Y3.AnalyticsTeam.CT.Cancer.service.IRedisService;
import com.Y3.AnalyticsTeam.CT.Cancer.service.MyRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class Redistest {

    @Autowired
    private IRedisService service;
    @Autowired
    private MyRedisService myRedisService;

    @Scheduled(initialDelayString="${jobs.initialDelay}", fixedRateString="${jobs.fixedRate}")
    public void test() {
        String key = "key_"+System.currentTimeMillis();
        String value = "val_"+System.currentTimeMillis();
//        service.put(key,value,-1);

        myRedisService.setString(key,value);
    }
}
