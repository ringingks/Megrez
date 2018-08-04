package com.Y3.AnalyticsTeam.CT.Cancer.DAO;

import com.Y3.AnalyticsTeam.CT.Cancer.DTO.SysRuleItemSet;
import com.Y3.AnalyticsTeam.CT.Cancer.DTO.SysRuleMainSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRuleDAO {

    List<SysRuleMainSet> queryRuMainSetByRefID(@Param("code")String code, @Param("status")String status);

    List<SysRuleItemSet> queryRuItemByMainid(@Param("mainID")String mainID);

}
