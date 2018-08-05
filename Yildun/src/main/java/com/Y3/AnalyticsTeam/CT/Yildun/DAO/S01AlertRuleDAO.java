package com.Y3.AnalyticsTeam.CT.Yildun.DAO;

import com.Y3.AnalyticsTeam.CT.Yildun.PO.S01AlertRulePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface S01AlertRuleDAO {

    List<S01AlertRulePO> findBycode(@Param("code") String code);
    
    List<Map<String,Object>> findLast30MinsLiveData();

    List<Map<String,Object>> findLastData();

    List<Map<String,Object>> querykeeper();

    void updateS01AlertRuleMainConf(S01AlertRulePO s01AlertRulePO);

    void insertS01AlertRuleMainConf(S01AlertRulePO s01AlertRulePO);

}
