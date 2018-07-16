package com.Y3.AnalyticsTeam.CT.provider.system.PO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class DicAgencyPO extends DictionaryEntity{

    private static final long serialVersionUID = -7616874836045845697L;

    @JSONField(ordinal=101)
    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
