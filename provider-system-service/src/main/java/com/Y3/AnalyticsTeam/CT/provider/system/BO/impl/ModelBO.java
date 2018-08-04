package com.Y3.AnalyticsTeam.CT.provider.system.BO.impl;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.AbstractBO;

public abstract class ModelBO extends AbstractBO {

    protected int calEndIdx(int page, int limit){
        return limit<0?-1:limit*page+1;
    }

    protected int calStartIdx(int page, int limit){
        int n = (page-1)*limit;
        return n>0?n:0;
    }
}
