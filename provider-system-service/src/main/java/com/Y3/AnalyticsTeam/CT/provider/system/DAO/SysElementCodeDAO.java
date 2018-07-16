package com.Y3.AnalyticsTeam.CT.provider.system.DAO;

import com.Y3.AnalyticsTeam.CT.provider.system.PO.SysEementCodePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysElementCodeDAO {
    List<SysEementCodePO> queryByEleCD(@Param("elementcode")String elementcode);
}
