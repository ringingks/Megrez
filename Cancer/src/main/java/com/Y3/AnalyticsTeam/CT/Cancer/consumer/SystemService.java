package com.Y3.AnalyticsTeam.CT.Cancer.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "PROVIDER-SYSTEM-SERVICE")
public interface SystemService {

    @RequestMapping(value = "/mng/api/call/{code}",method = RequestMethod.GET)
    String callAPIonGet(@PathVariable(value = "code") String code);
}
