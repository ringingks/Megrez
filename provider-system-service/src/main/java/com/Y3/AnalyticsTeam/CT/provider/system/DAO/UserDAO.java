package com.Y3.AnalyticsTeam.CT.provider.system.DAO;

import com.Y3.AnalyticsTeam.CT.provider.system.DTO.UserDTO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {

    CtUserPO findByID(@Param("_id") String _id);

    List<CtUserPO> findByName(@Param("_name") String _name);

    List<UserDTO> findByOnLimitRange(@Param("startindex") int startindex, @Param("endindex") int endindex);

    int countTotal();

    void update(CtUserPO user);

    void deleteByID(@Param("_id") int _id);

    void addByEntity(CtUserPO user);
}
