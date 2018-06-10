package com.Y3.AnalyticsTeam.CT.provider.report.BO;

import com.Y3.AnalyticsTeam.CT.provider.report.DTO.CtReportDTO;
import com.Y3.AnalyticsTeam.CT.provider.report.PO.CtReportPO;

import java.util.HashMap;
import java.util.List;

public interface IReportBO {

    public int countSettingOfAccessingAll();

    public List<CtReportDTO> findSettingOfAccessingAll(int page, int limit);

    public CtReportPO findSettingOfAccessingById(String _id);

    public void updateSettingOfAccessing(CtReportPO ctReport);

    public void addSettingOfAccessing(CtReportPO ctReport);

    public void deleteSettingOfAccessingByUID(int _id);

    public int deleteSettingOfAccessingsByUIDs(List<Integer> _ids);

    public List<HashMap> findRptByUserid(int userid);

}
