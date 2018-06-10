package com.Y3.AnalyticsTeam.CT.BO.impl;

import com.Y3.AnalyticsTeam.CT.BO.UserBO;
import com.Y3.AnalyticsTeam.CT.consumer.SystemService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBOImpl implements UserBO {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SystemService sysServ;

    @Override
    public JSONObject pwdChange(String uName,String oPwd,String nPwd) {

        String response = null;
        response = sysServ.findUserByName(uName);

        JSONObject reBack = new JSONObject();
        reBack.put("state","0");
        String infos = "";

        JSONObject resp = JSON.parseObject(response);
        JSONObject user;

        if(resp.getInteger("count")<1){
            infos = "no such account!";
            reBack.put("state","1");
            reBack.put("infos",infos);
            return reBack;
        }
        user = JSON.parseObject(response).getJSONArray("hits").getJSONObject(0);

        if(!user.getString("upasswd").equals(oPwd)){
            infos = "password is incorrect";
            reBack.put("state","0");
            reBack.put("infos",infos);
            return reBack;
        }

        user.put("upasswd",nPwd);
        response = sysServ.updateUser(user.toJSONString());

        if(JSON.parseObject(response).getString("state").equals("1")){
            infos = "update failed! ".concat(JSON.parseObject(response).getString("infos"));
            reBack.put("state","0");
            reBack.put("infos",infos);
        }

        infos = "ok";
        reBack.put("state","1");
        reBack.put("infos",infos);

        return reBack;
    }

    @Override
    public JSONObject loginCheck(String userNm, String userPwd) {
        JSONObject reBack = new JSONObject();
        reBack.put("state","0");
        String infos = "";

        String response = sysServ.findUserByName(userNm);
        if(response!=null){
            JSONObject json = JSONObject.parseObject( response );
            if(json.getString("state").equals("1")){
                JSONObject hit = json.getJSONArray("hits").getJSONObject(0);
                if( hit.getString("upasswd").equals(userPwd) ) {
                    reBack.put("state","1");
                    reBack.put("hits",json.getJSONArray("hits"));
                    infos = "ok";
                }else{
                    infos = "Incorrect password OR username is not exists!";
                }
            }else{
                infos = json.getString("infos");
            }
        }else{
            infos = "unkown Error! If you have tried it for many times, plz connect the system administrator!";
        }

        reBack.put("infos",infos);

        return reBack;
    }
}
