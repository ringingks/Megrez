package com.Y3.AnalyticsTeam.CT.provider.system.BO.impl;

import com.Y3.AnalyticsTeam.CT.Util.HttpHelp;
import com.Y3.AnalyticsTeam.CT.provider.system.BO.IManagementBO;
import com.Y3.AnalyticsTeam.CT.provider.system.DAO.CtMngApiDAO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtApiPO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtApiParamsPO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementBOImpl implements IManagementBO {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CtMngApiDAO apiDAO;

    @Override
    public JSONArray callAPI(String _code)throws Exception {

        // get the api from config
        CtApiPO apiConfig = apiDAO.queryByCode(_code);

        if(apiConfig==null){
           return null;
        }

        JSONArray jsonArray = new JSONArray();

        String _host = apiConfig.getHost();
        if(!_host.endsWith("/"))
            _host = _host+"/";
        String _path = apiConfig.getUrlpath();

        List<CtApiParamsPO> params = apiDAO.queryParamsByMainid(apiConfig.getUuid());

        String response = null;
        try {
            response = doGetRequest(_host+_path,params);
            if(response!=null && response.length()>0){
                Object json = JSON.parse(response);

                if(json instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject)json;
                    jsonArray.add(jsonObject);
                }else if (json instanceof JSONArray) {
                    jsonArray = (JSONArray) json;
                }

                return jsonArray;
            }
        } catch (Exception e) {
            LOGGER.error("### request exception! ", e);
            throw new Exception("request exception!");
        }

        return jsonArray;
    }

    @Override
    public int apiListCountTotal(){
        return apiDAO.countTotal();
    }


    @Override
    public List<CtApiPO> findApiList(int page, int limit) {
        int _start = (page-1)*limit;
        int _end = limit*page+1;

        return apiDAO.queryOnLimitRange(_start,_end);
    }

    @Override
    public List<CtApiParamsPO> findApiParamsList(String apiID) {
        return apiDAO.queryParamsByMainid(apiID);
    }

    @Override
    public int updateApi(CtApiPO apiPO) {
        if(apiPO.getUuid() == null){
            try {
                apiDAO.addAPI(apiPO);
                return 1;
            }catch (Exception e){
                LOGGER.error("### "+this.getClass().getSimpleName()+"updateApi exception",e);
            }
            return 0;
        }else {
            return apiDAO.updateAPI(apiPO);
        }
    }

    @Override
    public void deleteAPI(String uuid) {
        apiDAO.deleteAPIByID(uuid);
    }

    String doGetRequest(String requestPath, List<CtApiParamsPO> params) throws Exception {
        String paramStr = "t=511";
        for(CtApiParamsPO param:params){
            paramStr += "&" + param.getKey() + "=" + param.getValue();
        }

        return HttpHelp.doGet(requestPath+"?"+paramStr,"utf-8");
    }
}
