package com.Y3.AnalyticsTeam.CT.Cancer.DTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class SysRuleMainSet {

    private String name;

    private String suid;

    private String discription;

    private int alert_level;

    private int frequency;

    private String groupid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getAlert_level() {
        return alert_level;
    }

    public void setAlert_level(int alert_level) {
        this.alert_level = alert_level;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
