package com.Y3.AnalyticsTeam.CT.provider.system;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.ICaUserBO;
import com.Y3.AnalyticsTeam.CT.provider.system.DTO.UserDTO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CaUserPO;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/ru")
public class SysRuleService extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String findRuleByRefID(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return super.ResultsBuilder("1","ok",null,null);
    }


}
