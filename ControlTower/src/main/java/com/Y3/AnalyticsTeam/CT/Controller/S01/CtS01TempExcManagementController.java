package com.Y3.AnalyticsTeam.CT.Controller.S01;

import com.Y3.AnalyticsTeam.CT.Yildun.Action.AbstractModule;
import com.Y3.AnalyticsTeam.CT.consumer.S01Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("app/s01")
public class CtS01TempExcManagementController extends AbstractModule{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    S01Service s01Serv;

    @RequestMapping(value = "/f01/sender/conf", method = RequestMethod.GET)
    public @ResponseBody String getAlertSenderConf() {
        return s01Serv.findConfOfAlertsender();
    }

    @RequestMapping(value = "/f01/sender/conf", method = RequestMethod.POST)
    public @ResponseBody String updateConfOfAlertsender(@RequestParam("data") String data){
        return s01Serv.updateConfOfAlertsender(data);
    }

    @RequestMapping(value = "/f01/sender/data", method = RequestMethod.GET)
    public @ResponseBody String getLastThirtyMinsRecord(){
        return s01Serv.findLastThirtyMinsRecord();
    }

    @RequestMapping(value = "/f01/sender/last", method = RequestMethod.GET)
    public @ResponseBody String getLastRecord(){
        return s01Serv.findLastRecord();
    }

    @RequestMapping(value = "/f01/keepers", method = RequestMethod.GET)
    public @ResponseBody String getKeepers(){
        return s01Serv.findKeepers();
    }

}
