package com.Y3.AnalyticsTeam.CT.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "control-tower-backend")
public interface S01Service {

    @RequestMapping(value = "/app/s01/f01/conf",method = RequestMethod.GET)
    String findConfOfAlertsender();

    @RequestMapping(value = "/app/s01/f01/conf",method = RequestMethod.POST)
    String updateConfOfAlertsender(@RequestParam("data") String data);

    @RequestMapping(value = "/app/s01/f01/data/last30min",method = RequestMethod.GET)
    String findLastThirtyMinsRecord();

    @RequestMapping(value = "/app/s01/f01/data/last",method = RequestMethod.GET)
    String findLastRecord();

    @RequestMapping(value = "/app/s01/f01/data/keepers",method = RequestMethod.GET)
    String findKeepers();

}
