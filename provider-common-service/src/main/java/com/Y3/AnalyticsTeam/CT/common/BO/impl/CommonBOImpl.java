package com.Y3.AnalyticsTeam.CT.common.BO.impl;

import com.Y3.AnalyticsTeam.CT.common.BO.ICommonBO;
import com.Y3.AnalyticsTeam.CT.common.Util.HttpHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommonBOImpl implements ICommonBO {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${com.Y3.ControlTower.tableau.host}")
    String tableauServHost ;
    @Value(value = "${com.Y3.ControlTower.tableau.auth}")
    String auth;

    @Override
    public String getTableauTicket(String authorizedUser) {

        String _url = "";
        _url += tableauServHost;
        _url += "/trusted";
        LOGGER.info("### "+_url);
        Map<String,String> map = new HashMap<>();
        map.put("username",auth);

        String reponse = null;
        try {
            reponse = HttpHelp.doPost(_url,map,"utf-8");
        } catch (Exception e) {
            LOGGER.error("### request->"+_url,e);
        }
        return reponse;
    }

    @Override
    public String sendGetRequest(String urlPath) {
        return null;
    }
}
