package com.Y3.AnalyticsTeam.CT.provider.system.BO;

import java.util.List;

public interface ICaMenuBO {

    /***
     * find menu list by userid
     * @param userid
     * @return
     */
    public List findMenuByUserid(String userid);
}