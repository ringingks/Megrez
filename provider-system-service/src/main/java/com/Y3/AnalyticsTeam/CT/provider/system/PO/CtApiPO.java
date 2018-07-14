package com.Y3.AnalyticsTeam.CT.provider.system.PO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class CtApiPO extends StandardEntity{

    private static final long serialVersionUID = -6563302621395358150L;

    /**
     * uuid
     */
    @JSONField(ordinal=1)
    private String uuid;

    /**
     * code
     */
    @JSONField(ordinal=2)
    private String code;

    /**
     * discription

     */
    @JSONField(ordinal=3)
    private String discription;

    /**
     * host
     */
    @JSONField(ordinal=3)
    private String host;

    /**
     * urlpath
     */
    @JSONField(ordinal=4)
    private String urlpath;

    /**
     * params
     */
    @JSONField(ordinal=5)
    private String params;

    /**
     * ascription
     */
    @JSONField(ordinal=6)
    private String ascription;

    @JSONField(ordinal=7)
    private String method;

    @JSONField(ordinal=8)
    private int status;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrlpath() {
        return urlpath;
    }

    public void setUrlpath(String urlpath) {
        this.urlpath = urlpath;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
