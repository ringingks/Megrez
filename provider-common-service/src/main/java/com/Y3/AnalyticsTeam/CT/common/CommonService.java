package com.Y3.AnalyticsTeam.CT.common;

import com.Y3.AnalyticsTeam.CT.common.BO.ICommonBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comm")
public class CommonService extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ICommonBO commonBO;

    @RequestMapping(value = "/ticket/tableau/{account}", method = RequestMethod.GET)
    public String findUserByID(@PathVariable(value="account") String account) {
        return super.ResultsBuilder("1","ok",commonBO.getTableauTicket(account),null);
    }



}
