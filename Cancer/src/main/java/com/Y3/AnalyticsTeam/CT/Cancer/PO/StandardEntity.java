package com.Y3.AnalyticsTeam.CT.Cancer.PO;

import com.alibaba.fastjson.annotation.JSONField;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class StandardEntity extends Entity {

    /**
     * 创建时间
     */
    @JSONField(ordinal=9998)
    private String createtime;

    /**
     * 最近更新时间
     */
    @JSONField(ordinal=9999)
    private String lastupdatetime;


    public String getCreatetime() {
        if(this.createtime!=null && !createtime.equals("")){
            return this.createtime;
        }else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format(new Date());
        }
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLastupdatetime() {
        if(this.lastupdatetime!=null && !lastupdatetime.equals("")){
            return this.lastupdatetime;
        }else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            return formatter.format(new Date());
        }
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}
