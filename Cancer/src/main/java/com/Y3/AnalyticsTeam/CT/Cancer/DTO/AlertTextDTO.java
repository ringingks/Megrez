package com.Y3.AnalyticsTeam.CT.Cancer.DTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class AlertTextDTO {

    private String keeper;
    private String tel;
    private String email;
    private String subject;
    private String text;

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
