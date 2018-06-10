package com.Y3.AnalyticsTeam.CT.provider.system.BO.impl;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.IUserBO;
import com.Y3.AnalyticsTeam.CT.provider.system.DAO.UserDAO;
import com.Y3.AnalyticsTeam.CT.provider.system.DTO.UserDTO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtUserPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserBOImpl implements IUserBO {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserDAO userDAO;

    @Override
    public CtUserPO findUserbyId(String _id) {
        return userDAO.findByID(_id);
    }

    @Override
    public List<CtUserPO> findUserbyName(String _name) {
        return userDAO.findByName(_name);
    }

    @Override
    public List<UserDTO> findUserOnPage(int page, int limit) {
        int _start = (page-1)*limit;
        int _end = limit*page+1;

        return userDAO.findByOnLimitRange(_start,_end);
    }

    @Override
    public int countUserTotal() {
        return userDAO.countTotal();
    }

    @Override
    public void updateUser(CtUserPO userPO) throws Exception {
        userDAO.update(userPO);
    }

    @Override
    public int deleteUserByIDs(List<Integer> _ids) {
        int cnt = 0;

        for(int _id : _ids) {
            try {
                userDAO.deleteByID(_id);
                cnt++;
            } catch (Exception ex) {
                LOGGER.error("### delete error on [UID=]" + _id, ex);
            }
        }

        return cnt;
    }

    @Override
    public void addUser(CtUserPO userPO) throws Exception {
        userDAO.addByEntity(userPO);
    }
}
