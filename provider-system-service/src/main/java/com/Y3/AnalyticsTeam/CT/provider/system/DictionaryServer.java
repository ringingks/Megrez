package com.Y3.AnalyticsTeam.CT.provider.system;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.IDictionaryBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys/dic")
public class DictionaryServer extends AbstractModule{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IDictionaryBO dicBO;

    @RequestMapping(value = "/agency", method = RequestMethod.GET)
    public String findByEleCodeOnPage(@RequestParam("page") int page, @RequestParam("limit") int limit){
        return super.ResultsBuilder("1","ok",dicBO.findAgencyByOnPage(page,limit),null);
    }
}
