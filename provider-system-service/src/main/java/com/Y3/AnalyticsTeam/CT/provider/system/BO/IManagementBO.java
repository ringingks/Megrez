package com.Y3.AnalyticsTeam.CT.provider.system.BO;

import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtApiPO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtApiParamsPO;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

public interface IManagementBO {

    /***
     * call API by its unique code
     * @param _code
     * @return
     * @throws Exception
     */
    JSONArray callAPI(String _code) throws Exception;

    /***
     * get a total count of API list
     * @return
     */
    int apiListCountTotal();

    /**
     * get API list
     * @param page
     * @param limit
     * @return
     */
    List<CtApiPO> findApiList(int page, int limit);

    /**
     * get whole API's params
     * @param apiID
     * @return
     */
    List<CtApiParamsPO> findApiParamsList(String apiID);

    /**
     * update an API
     * @param apiPO
     */
    int updateApi(CtApiPO apiPO);

    void deleteAPI(String uuid);
}
