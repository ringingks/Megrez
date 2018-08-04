package com.Y3.AnalyticsTeam.CT.provider.system.DAO;

import com.Y3.AnalyticsTeam.CT.provider.system.PO.RuGroupPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRuleDAO {

    List<RuGroupPO> queryRuGroup(String refid);
}
