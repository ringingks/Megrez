package com.Y3.AnalyticsTeam.CT.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "PROVIDER-SYSTEM-SERVICE")
public interface SystemService {

    @RequestMapping(value = "/sys/user/name/{username}",method = RequestMethod.GET)
    String findUserByName(@RequestParam(value = "username") String username);

    @RequestMapping(value = "/sys/user",method = RequestMethod.GET)
    String findUserOnPage(@RequestParam(value = "page") int page,@RequestParam(value = "limit") int limit);

    @RequestMapping(value = "/sys/user",method = RequestMethod.POST)
    String updateUser(@RequestParam(value = "data") String json);

    @RequestMapping(value = "/sys/user",method = RequestMethod.PUT)
    String addUser(@RequestParam(value = "data") String json);

    @RequestMapping(value = "/sys/user",method = RequestMethod.DELETE)
    String delUser(@RequestParam(value = "data") String json);
}
