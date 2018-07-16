package com.Y3.AnalyticsTeam.CT.provider.system.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictionaryDAO {

    /***
     *
     * @param tablecode
     * @param status
     * @return
     */
    int count(@Param("tablecode")String tablecode,@Param("status")int status);
}
