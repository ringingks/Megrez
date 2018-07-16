package com.Y3.AnalyticsTeam.CT.provider.system.BO;

import com.Y3.AnalyticsTeam.CT.provider.system.PO.DicAgencyPO;

import java.util.List;

public interface IDictionaryBO {

    /***
     *
     * @param page
     * @param limit
     * @return
     */
    List<DicAgencyPO> findAgencyByOnPage(int page , int limit);

    /***
     *
     * @param elementcode
     * @param status
     * @return
     */
    int countDicEnum(String elementcode, int status);
}
