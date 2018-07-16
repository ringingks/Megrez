package com.Y3.AnalyticsTeam.CT.provider.system.DAO;

import com.Y3.AnalyticsTeam.CT.provider.system.PO.DicAgencyPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DicAgencyDAO {

    List<DicAgencyPO> queryAgencyEnum(@Param("startindex") int startindex, @Param("endindex") int endindex);

}
