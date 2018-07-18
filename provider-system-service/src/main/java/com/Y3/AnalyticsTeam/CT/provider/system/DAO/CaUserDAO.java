package com.Y3.AnalyticsTeam.CT.provider.system.DAO;

import com.Y3.AnalyticsTeam.CT.provider.system.DTO.UserDTO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CaUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CaUserDAO {

    List<UserDTO> queryUserOnPage(@Param("startindex") int startindex, @Param("endindex") int endindex);

    int count(@Param("status") int status);

    String add(CaUserPO user);
}
