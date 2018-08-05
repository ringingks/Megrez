package com.Y3.AnalyticsTeam.CT.Yildun.BO;

import com.Y3.AnalyticsTeam.CT.Yildun.PO.S01AlertRulePO;

import java.util.List;
import java.util.Map;

public interface IS01TempExManagementBO {

    List<S01AlertRulePO> findS01AlertRule();

    Map<String,Object> findLast30MinsData();

    List<Map<String,Object>> findLastData();

    List<Map<String,Object>> findKeeperList();

    void updateS01AlertRuleMainConf(S01AlertRulePO s01AlertRulePO);
}
