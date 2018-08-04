package com.Y3.AnalyticsTeam.CT.backend.BO.Impl;

import com.Y3.AnalyticsTeam.CT.backend.BO.IS01TempExManagementBO;
import com.Y3.AnalyticsTeam.CT.backend.DAO.S01AlertRuleDAO;
import com.Y3.AnalyticsTeam.CT.backend.PO.S01AlertRulePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class S01TempExManagementBOImpl implements IS01TempExManagementBO {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    S01AlertRuleDAO s01AlertRuleDAO;

    @Override
    public List<S01AlertRulePO> findS01AlertRule() {
        return s01AlertRuleDAO.findBycode("01");
    }

    @Override
    public Map<String,List<Map<String,Object>>> findLast30MinsData() {
        List<Map<String, Object>> query = s01AlertRuleDAO.findLast30MinsLiveData();
        Map<String,List<Map<String,Object>>> rback = new HashMap<String,List<Map<String,Object>>>();
        for(Map<String, Object> q : query){
            if(q.get("devicegroup") != null) {
                String deviceName = q.get("devicegroup").toString();
                if (rback.containsKey(deviceName)) {
                    rback.get(deviceName).add(q);
                } else {
                    List<Map<String, Object>> li = new ArrayList<>();
                    li.add(q);
                    rback.put(deviceName, li);
                }
            }else{
                continue;
            }
        }
        return rback;
    }

    @Override
    public List<Map<String, Object>> findLastData() {
        return s01AlertRuleDAO.findLastData();
    }

    @Override
    public List<Map<String, Object>> findKeeperList() {
        return s01AlertRuleDAO.querykeeper();
    }

    @Override
    public void updateS01AlertRuleMainConf(S01AlertRulePO s01AlertRulePO) {
        String _id = s01AlertRulePO.getBillid();
        if(_id == null || _id.equals("")){ // 无id，为更新操作
            s01AlertRuleDAO.insertS01AlertRuleMainConf(s01AlertRulePO);
        }else{
            s01AlertRuleDAO.updateS01AlertRuleMainConf(s01AlertRulePO);
        }
    }

}
