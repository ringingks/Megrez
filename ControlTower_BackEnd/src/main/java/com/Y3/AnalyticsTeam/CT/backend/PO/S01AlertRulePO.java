package com.Y3.AnalyticsTeam.CT.backend.PO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class S01AlertRulePO extends StandardEntity {

    private static final long serialVersionUID = -4752217889987556574L;

    /***
     * code
     */
    @JSONField(ordinal=1)
    private String code;
    /***
     * billid
     */
    @JSONField(ordinal=2)
    private String billid;
    /***
     * discription
     */
    @JSONField(ordinal=3)
    private String discription;
    /***
     * frequency
     */
    @JSONField(ordinal=4)
    private int frequency;
    /***
     * status
     */
    @JSONField(ordinal=5)
    private int status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
