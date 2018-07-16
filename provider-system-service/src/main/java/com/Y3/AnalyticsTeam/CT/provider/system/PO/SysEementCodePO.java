package com.Y3.AnalyticsTeam.CT.provider.system.PO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class SysEementCodePO extends StandardEntity {

    private static final long serialVersionUID = 3065382362393292811L;
    /***
     * suid
     */
    @JSONField(ordinal=1)
    private String suid;
    /***
     * elemanecode
     */
    @JSONField(ordinal=2)
    private String elemanecode;
    /***
     * tableview
     */
    @JSONField(ordinal=3)
    private String tableview;

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getElemanecode() {
        return elemanecode;
    }

    public void setElemanecode(String elemanecode) {
        this.elemanecode = elemanecode;
    }

    public String getTableview() {
        return tableview;
    }

    public void setTableview(String tableview) {
        this.tableview = tableview;
    }
}
