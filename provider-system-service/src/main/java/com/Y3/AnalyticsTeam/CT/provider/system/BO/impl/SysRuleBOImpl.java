package com.Y3.AnalyticsTeam.CT.provider.system.BO.impl;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.ISysRuleBO;
import com.Y3.AnalyticsTeam.CT.provider.system.DAO.SysRuleDAO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.RuGroupPO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysRuleBOImpl implements ISysRuleBO {

    @Autowired
    SysRuleDAO ruDAO;

    @Override
    public boolean verification(String rugroupid) {

        List<RuGroupPO> reGroups =  ruDAO.queryRuGroup("");
        for(RuGroupPO reGroup : reGroups){

        }
        return false;
    }
}
