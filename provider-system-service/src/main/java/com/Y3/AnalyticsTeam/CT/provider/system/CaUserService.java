package com.Y3.AnalyticsTeam.CT.provider.system;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.ICaUserBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/user")
public class CaUserService extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ICaUserBO userBO;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findUserOnPage(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return super.ResultsBuilder("1","ok",userBO.findUserOnPage(page,limit),null,userBO.count(-1));
    }

}
