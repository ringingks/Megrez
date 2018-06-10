package com.Y3.AnalyticsTeam.CT.provider.report.DAO;

import com.Y3.AnalyticsTeam.CT.provider.report.DTO.CtReportDTO;
import com.Y3.AnalyticsTeam.CT.provider.report.PO.CtReportPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ReportDAO {

    int countCtReportWholeSize();

    List<CtReportDTO> findCtReportAll();

    List<CtReportDTO> findCtReportAllByPage(@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

    CtReportPO findCtReportByID(@Param("_id") String _id);

    void updateCtReport(CtReportPO ctReport);

    void addCtReport(CtReportPO ctReport);

    void delCtReport(int uid);

    List<HashMap> findCtReportByUserid(int userid);

}
