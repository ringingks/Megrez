package com.Y3.AnalyticsTeam.CT.provider.report.BO.impl;

import com.Y3.AnalyticsTeam.CT.provider.report.BO.IReportBO;
import com.Y3.AnalyticsTeam.CT.provider.report.DAO.ReportDAO;
import com.Y3.AnalyticsTeam.CT.provider.report.DTO.CtReportDTO;
import com.Y3.AnalyticsTeam.CT.provider.report.PO.CtReportPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReportBOImpl implements IReportBO {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReportDAO rptDAO;

    @Override
    public int countSettingOfAccessingAll() {
        return rptDAO.countCtReportWholeSize();
    }

    @Override
    public List<CtReportDTO> findSettingOfAccessingAll(int page, int limit) {
        if(page==-1 || limit==-1) {
            return rptDAO.findCtReportAll();
        }else{
            int startIndex=(page-1)*(limit);
            int endIndex = startIndex + limit + 1;
            return rptDAO.findCtReportAllByPage(startIndex,endIndex);
        }
    }

    @Override
    public CtReportPO findSettingOfAccessingById(String _id) {
        return rptDAO.findCtReportByID(_id);
    }

    @Override
    public void updateSettingOfAccessing(CtReportPO ctReport) {
        rptDAO.updateCtReport(ctReport);
    }

    @Override
    public void addSettingOfAccessing(CtReportPO ctReport) {
        rptDAO.addCtReport(ctReport);
    }

    @Override
    public void deleteSettingOfAccessingByUID(int _id) {
        rptDAO.delCtReport(_id);
    }

    @Override
    public int deleteSettingOfAccessingsByUIDs(List<Integer> _ids) {

        int cnt = 0;

        for(int _id : _ids){
            try {
                rptDAO.delCtReport(_id);
                cnt++;
            }catch (Exception ex){
                LOGGER.error("### delete error on [UID=]"+_id,ex);
            }

        }
        return cnt;
    }

    @Override
    public List<HashMap> findRptByUserid(int userid){
        return rptDAO.findCtReportByUserid(userid);
    }
}
