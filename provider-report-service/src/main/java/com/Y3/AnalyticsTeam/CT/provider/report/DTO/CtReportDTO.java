package com.Y3.AnalyticsTeam.CT.provider.report.DTO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public class CtReportDTO {

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public int getRepid() {
        return repid;
    }

    public void setRepid(int repid) {
        this.repid = repid;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public String getReporturl() {
        return reporturl;
    }

    public void setReporturl(String reporturl) {
        this.reporturl = reporturl;
    }

    public String getUrlparam() {
        return urlparam;
    }

    public void setUrlparam(String urlparam) {
        this.urlparam = urlparam;
    }

    public String getTicketuser() {
        return ticketuser;
    }

    public void setTicketuser(String ticketuser) {
        this.ticketuser = ticketuser;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    /**
     * rownum
     */
    @JSONField(ordinal=0)
    private int rn;
    /**
     * repid
     */
    @JSONField(ordinal=1)
    private int repid;
    /**
     * reportname
     */
    @JSONField(ordinal=2)
    private String reportname;
    /**
     * reporturl
     */
    @JSONField(ordinal=3)
    private String reporturl;
    /**
     * urlparam
     */
    @JSONField(ordinal=4)
    private String urlparam;
    /**
     * ticketuser
     */
    @JSONField(ordinal=5)
    private String ticketuser;
    /**
     * servername
     */
    @JSONField(ordinal=6)
    private String servername;

}
