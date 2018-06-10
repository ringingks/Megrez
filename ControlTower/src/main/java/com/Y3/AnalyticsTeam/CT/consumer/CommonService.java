package com.Y3.AnalyticsTeam.CT.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("PROVIDER-COMMON-SERVICE")
public interface CommonService {

    @RequestMapping(value = "/comm/ticket/tableau/{account}",method = RequestMethod.GET)
    String getReportServAuthTicket(@PathVariable(value="account") String account);
}
