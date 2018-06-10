package com.Y3.AnalyticsTeam.CT.provider.system.PO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class CtUserPO extends Entity {

    private static final long serialVersionUID = -1548932192921636754L;
    /**
     * USERID
     */
    @JSONField(ordinal=1)
    private int userid;
    /**
     * 用户名 USERNAME
     */
    @JSONField(ordinal=2)
    private String username;
    /**
     * 用户密码 UPASSWD
     */
    @JSONField(ordinal=3)
    private String upasswd;
    /**
     * 机构id ORGID
     */
    @JSONField(ordinal=4)
    private int orgid;
    /**
     * ACTIVE_IND
     */
    @JSONField(ordinal=5)
    private int active_ind;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpasswd() {
        return upasswd;
    }

    public void setUpasswd(String upasswd) {
        this.upasswd = upasswd;
    }

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public int getActive_ind() {
        return active_ind;
    }

    public void setActive_ind(int active_ind) {
        this.active_ind = active_ind;
    }
}
