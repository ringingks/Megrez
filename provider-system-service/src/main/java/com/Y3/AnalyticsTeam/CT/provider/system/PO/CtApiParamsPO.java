package com.Y3.AnalyticsTeam.CT.provider.system.PO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class CtApiParamsPO extends StandardEntity{

    private static final long serialVersionUID = 2863887802857757443L;
    /**
     * uuid
     */
    @JSONField(ordinal=1)
    private String uuid;
    /**
     * mainid
     */
    @JSONField(ordinal=2)
    private String mainid;
    /**
     * key
     */
    @JSONField(ordinal=3)
    private String key;
    /**
     * value
     */
    @JSONField(ordinal=4)
    private String value;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMainid() {
        return mainid;
    }

    public void setMainid(String mainid) {
        this.mainid = mainid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
