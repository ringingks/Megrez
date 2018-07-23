package com.Y3.AnalyticsTeam.CT.Cancer.PO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class CtRecApiResponse extends StandardEntity {
    private static final long serialVersionUID = -3011111036176182120L;

    /**
     * recid
     */
    @JSONField(ordinal=1)
    private String recid;
    /**
     * mainid
     */
    @JSONField(ordinal=2)
    private String mainid;
    /**
     * response
     */
    @JSONField(ordinal=3)
    private String response;

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
