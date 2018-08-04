package com.Y3.AnalyticsTeam.CT.Cancer.DAO;

import com.Y3.AnalyticsTeam.CT.Cancer.DTO.AlertTextDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface S01AlertActionDAO {

    List<AlertTextDTO> queryAlertText(@Param("rugroup")String rugroup);
}
