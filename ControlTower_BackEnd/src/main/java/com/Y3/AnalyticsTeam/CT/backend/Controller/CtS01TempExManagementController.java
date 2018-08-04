package com.Y3.AnalyticsTeam.CT.backend.Controller;

import com.Y3.AnalyticsTeam.CT.backend.BO.IS01TempExManagementBO;
import com.Y3.AnalyticsTeam.CT.backend.PO.S01AlertRulePO;
import com.Y3.AnalyticsTeam.CT.backend.PO.S01RecHPBLivePO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/s01")
public class CtS01TempExManagementController extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IS01TempExManagementBO tempExManagementBO;

    @RequestMapping(value = "/f01/conf", method = RequestMethod.GET)
    public String findConf4AlertSender() {
        return super.ResultsBuilder("1","ok",tempExManagementBO.findS01AlertRule(),null);
    }

    @RequestMapping(value = "/f01/data/last30min", method = RequestMethod.GET)
    public String findLastThirtyMinsData() {
        return super.ResultsBuilder("1","ok",tempExManagementBO.findLast30MinsData(),null);
    }

    @RequestMapping(value = "/f01/data/last", method = RequestMethod.GET)
    public String findLastRecord(){
        return super.ResultsBuilder("1","ok",tempExManagementBO.findLastData(),null);
    }

    @RequestMapping(value = "/f01/data/keepers", method = RequestMethod.GET)
    public String findKeepers(){
        return super.ResultsBuilder("1","ok",tempExManagementBO.findKeeperList(),null);
    }

    @RequestMapping(value = "/f01/conf", method = RequestMethod.POST)
    public String updateConf4AlertSender(@RequestParam("data") String data){
        JSONObject json = JSON.parseObject(data);
        S01AlertRulePO alertRulePO = convert2RecHPBLivePO(json);
        tempExManagementBO.updateS01AlertRuleMainConf(alertRulePO);
        return super.ResultsBuilder("1","ok",alertRulePO.getBillid(),null);
    }

    private S01AlertRulePO convert2RecHPBLivePO(JSONObject json){
        S01AlertRulePO alertRulePO = new S01AlertRulePO();

        if(json.getString("billid")!=null && !json.getString("billid").equals("")){
            alertRulePO.setBillid(json.getString("billid"));
        }
        if(json.getString("code")!=null && !json.getString("code").equals("")){
            alertRulePO.setCode(json.getString("code"));
        }
        if(json.getString("discription")!=null && !json.getString("discription").equals("")){
            alertRulePO.setDiscription(json.getString("discription"));
        }
        if(json.getString("frequency")!=null && !json.getString("frequency").equals("")){
            alertRulePO.setFrequency(json.getIntValue("frequency"));
        }
        if(json.getString("status")!=null && !json.getString("status").equals("")){
            alertRulePO.setStatus(json.getIntValue("status"));
        }

        return alertRulePO;
    }

}
