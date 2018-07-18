package com.Y3.AnalyticsTeam.CT.provider.system.BO.impl;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.ICaUserBO;
import com.Y3.AnalyticsTeam.CT.provider.system.DAO.CaUserDAO;
import com.Y3.AnalyticsTeam.CT.provider.system.DTO.UserDTO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CaUserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaUserBOImpl extends ModelBO implements ICaUserBO {

    @Autowired
    CaUserDAO caUserDAO;

    @Override
    public List<UserDTO> findUserOnPage(int page, int limit) {
        int startindex = super.calStartIdx(page,limit);
        int endindex = super.calEndIdx(page,limit);
        return caUserDAO.queryUserOnPage(startindex,endindex);
    }

    @Override
    public int count(int status) {
        return caUserDAO.count(status);
    }

    @Override
    public String addNewUser(CaUserPO user) {
        return caUserDAO.add(user);
    }
}
