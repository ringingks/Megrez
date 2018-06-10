package com.Y3.AnalyticsTeam.CT.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PROVIDER-REPORT-SERVICE")
public interface ReportService {

    @RequestMapping(value = "/rpt/setting/accessing",method = RequestMethod.GET)
    String findReportAccessingSetting(@RequestParam(value = "limit") int limit,@RequestParam(value = "page") int page);

    @RequestMapping(value = "/rpt/setting/accessing",method = RequestMethod.POST)
    String updateReportAccessingSetting(@RequestParam(value = "data") String json);

    @RequestMapping(value = "/rpt/setting/accessing",method = RequestMethod.PUT)
    String addReportAccessingSetting(@RequestParam(value = "data") String json);

    @RequestMapping(value = "/rpt/setting/accessing",method = RequestMethod.DELETE)
    String delReportAccessingSetting(@RequestParam(value = "data") String json);


    @RequestMapping(value = "/rpt/user/{userid}",method = RequestMethod.GET)
    String findReportByUserid(@PathVariable(value="userid") int userid);

}
