package com.Y3.AnalyticsTeam.CT.backend.BO;

import com.Y3.AnalyticsTeam.CT.backend.PO.S01AlertRulePO;

import java.util.List;
import java.util.Map;

public interface IS01TempExManagementBO {

    List<S01AlertRulePO> findS01AlertRule();

    Map<String,List<Map<String,Object>>> findLast30MinsData();

    List<Map<String,Object>> findLastData();

    List<Map<String,Object>> findKeeperList();

    void updateS01AlertRuleMainConf(S01AlertRulePO s01AlertRulePO);
}
