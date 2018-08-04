package com.Y3.AnalyticsTeam.CT.provider.system.PO;

public class RuGroupPO extends Entity {


    private static final long serialVersionUID = 7616620156628849860L;

    /***
     * suid
     */
    private String suid;
    /***
     * name
     */
    private String name;
    /***
     * status
     */
    private int status;
    /***
     * refid
     */
    private String refid;

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRefid() {
        return refid;
    }

    public void setRefid(String refid) {
        this.refid = refid;
    }
}
