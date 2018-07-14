package com.Y3.AnalyticsTeam.CT.provider.system;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.IUserBO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtUserPO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ca/menu")
public class CaMenuService extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IUserBO userBO;

    @RequestMapping(value = "/user/{_id}", method = RequestMethod.GET)
    public String findUserByID(@PathVariable(value="_id") String _id) {
        return super.ResultsBuilder("1","ok",userBO.findUserbyId(_id),null);
    }

}
