package com.Y3.AnalyticsTeam.CT.provider.system.PO;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public class CaUserPO extends StandardEntity{

    private static final long serialVersionUID = -5761157867155150708L;

    /***
     * userid
     */
    @JSONField(ordinal=1)
    private String userid;
    /***
     * code
     */
    @JSONField(ordinal=2)
    private String code;
    /***
     * name
     */
    @JSONField(ordinal=3)
    private String name;
    /***
     * password
     */
    @JSONField(ordinal=4)
    private String password;
    /***
     * managerid
     */
    @JSONField(ordinal=5)
    private String managerid;
    /***
     * orgid
     */
    @JSONField(ordinal=6)
    private String orgid;
    /***
     * status
     */
    @JSONField(ordinal=7)
    private int status;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
