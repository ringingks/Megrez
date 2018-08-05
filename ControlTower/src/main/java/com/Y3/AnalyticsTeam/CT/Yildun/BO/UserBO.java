package com.Y3.AnalyticsTeam.CT.Yildun.BO;

import com.alibaba.fastjson.JSONObject;

public interface UserBO {

    /***
     * 修改密码
     * @param uName
     * @param oPwd
     * @param nPwd
     */
    public JSONObject pwdChange(String uName, String oPwd, String nPwd);

    /***
     * login info checking
     * @param userNm
     * @param userPwd
     */
    public JSONObject loginCheck(String userNm,String userPwd);
}
