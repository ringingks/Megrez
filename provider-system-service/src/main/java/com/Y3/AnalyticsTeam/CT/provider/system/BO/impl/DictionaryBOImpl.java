package com.Y3.AnalyticsTeam.CT.provider.system.BO.impl;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.AbstractBO;
import com.Y3.AnalyticsTeam.CT.provider.system.BO.IDictionaryBO;
import com.Y3.AnalyticsTeam.CT.provider.system.DAO.DicAgencyDAO;
import com.Y3.AnalyticsTeam.CT.provider.system.DAO.DictionaryDAO;
import com.Y3.AnalyticsTeam.CT.provider.system.DAO.SysElementCodeDAO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.DicAgencyPO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.SysEementCodePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryBOImpl extends AbstractBO implements IDictionaryBO {

    @Autowired
    SysElementCodeDAO  sysElementCodeDAO;
    @Autowired
    DictionaryDAO dicDAO;
    @Autowired
    DicAgencyDAO dicAgencyDAO;

    @Override
    public List<DicAgencyPO> findAgencyByOnPage(int page, int limit) {
        int startindex = 0;
        int endindex = -1;
        return dicAgencyDAO.queryAgencyEnum(startindex,endindex);
    }

    @Override
    public int countDicEnum(String elementcode, int status) {
        List<SysEementCodePO> elementcodeSets = sysElementCodeDAO.queryByEleCD(elementcode);
        if( elementcodeSets==null || elementcodeSets.isEmpty() ){
            return 0;
        }
        SysEementCodePO sysEementCodePO = elementcodeSets.get(0);
        String tableview = sysEementCodePO.getTableview();


        return 0;
    }
}
