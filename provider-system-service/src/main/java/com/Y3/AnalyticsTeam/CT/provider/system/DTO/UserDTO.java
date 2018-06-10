package com.Y3.AnalyticsTeam.CT.provider.system.DTO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class UserDTO {

    @JSONField(ordinal=0)
    private int rn;
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
    private String userpwd;
    /**
     * 机构id ORGID
     */
    @JSONField(ordinal=4)
    private int orgid;
    /**
     * 机构id ORGID
     */
    @JSONField(ordinal=5)
    private String orgname;
    /**
     * ACTIVE_IND
     */
    @JSONField(ordinal=6)
    private int status;

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

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

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
