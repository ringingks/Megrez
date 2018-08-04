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
    private String userid;
    /**
     * 用户编码 USERCODE
     */
    @JSONField(ordinal=2)
    private String usercode;
    /**
     * 用户名 USERNAME
     */
    @JSONField(ordinal=3)
    private String username;
    /**
     * 用户密码 UPASSWD
     */
//    @JSONField(ordinal=4)
//    private String userpwd;
    /**
     *
     */
    @JSONField(ordinal=5)
    private String managerid;
    /**
     *
     */
    @JSONField(ordinal=6)
    private String managercode;
    /**
     *
     */
    @JSONField(ordinal=7)
    private String managername;
    /**
     * 机构id ORGID
     */
    @JSONField(ordinal=8)
    private String orgid;
    /**
     * 机构id agencyname
     */
    @JSONField(ordinal=9)
    private String agencyname;
    /**
     * ACTIVE_IND
     */
    @JSONField(ordinal=10)
    private int status;

    @JSONField(ordinal=10)
    private String userpwd;

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    public String getManagercode() {
        return managercode;
    }

    public void setManagercode(String managercode) {
        this.managercode = managercode;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
}
