package com.Y3.AnalyticsTeam.CT.provider.system.PO;

import com.alibaba.fastjson.annotation.JSONField;

public class DictionaryEntity extends StandardEntity {

    /***
     * itemid
     */
    @JSONField(ordinal=1)
    private String itemid;
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
     * parentid
     */
    @JSONField(ordinal=4)
    private String parentid;
    /***
     * status
     */
    @JSONField(ordinal=5)
    private int status;
    /***
     * isleaf
     */
    @JSONField(ordinal=6)
    private int isleaf;
    /***
     * levelno
     */
    @JSONField(ordinal=7)
    private int levelno;
    /***
     * wholename
     */
    @JSONField(ordinal=8)
    private String wholename;
    /***
     * shortname
     */
    @JSONField(ordinal=9)
    private String shortname;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(int isleaf) {
        this.isleaf = isleaf;
    }

    public int getLevelno() {
        return levelno;
    }

    public void setLevelno(int levelno) {
        this.levelno = levelno;
    }

    public String getWholename() {
        return wholename;
    }

    public void setWholename(String wholename) {
        this.wholename = wholename;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

}
