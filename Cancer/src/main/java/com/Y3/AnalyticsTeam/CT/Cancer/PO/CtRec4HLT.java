package com.Y3.AnalyticsTeam.CT.Cancer.PO;

import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public class CtRec4HLT extends StandardEntity {
    private static final long serialVersionUID = -3323352731847407054L;

    private String recid;

    private String device_name;

    private String device_batterylevel;

    private BigDecimal temperature;

    private String unit;

    private String locationguid;

    private String createtime;

    private String response_batch;

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_batterylevel() {
        return device_batterylevel;
    }

    public void setDevice_batterylevel(String device_batterylevel) {
        this.device_batterylevel = device_batterylevel;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocationguid() {
        return locationguid;
    }

    public void setLocationguid(String locationguid) {
        this.locationguid = locationguid;
    }

    @Override
    public String getCreatetime() {
        return createtime;
    }

    @Override
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getResponse_batch() {
        return response_batch;
    }

    public void setResponse_batch(String response_batch) {
        this.response_batch = response_batch;
    }
}
