package com.Y3.AnalyticsTeam.CT.provider.system.DAO;

import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtApiPO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtApiParamsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CtMngApiDAO {

    List<CtApiPO> queryOnLimitRange(@Param("startindex") int startindex, @Param("endindex") int endindex);

    int countTotal();

    CtApiPO queryByCode(@Param("code") String code);

    List<CtApiParamsPO> queryParamsByMainid(@Param("mainid") String mainid);

    int updateAPI(CtApiPO apiPO);

    void addAPI(CtApiPO apiPO);

    void deleteAPIByID(@Param("uuid")String uuid);
}
