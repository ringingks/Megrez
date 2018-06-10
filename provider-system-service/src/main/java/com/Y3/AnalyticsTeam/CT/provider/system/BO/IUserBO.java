package com.Y3.AnalyticsTeam.CT.provider.system.BO;

import com.Y3.AnalyticsTeam.CT.provider.system.DTO.UserDTO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtUserPO;

import java.util.List;

public interface IUserBO {

    public CtUserPO findUserbyId(String _id);

    public List<CtUserPO> findUserbyName(String _name);

    public List<UserDTO> findUserOnPage(int page, int limit);

    public int countUserTotal();

    public void updateUser(CtUserPO userPO) throws Exception;

    public int deleteUserByIDs(List<Integer> _ids);

    public void addUser(CtUserPO userPO) throws Exception;
}
