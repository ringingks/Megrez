package com.Y3.AnalyticsTeam.CT.provider.system.BO.impl;

public abstract class ModelBO {

    protected int calEndIdx(int page, int limit){
        return limit<0?-1:limit*page+1;
    }

    protected int calStartIdx(int page, int limit){
        int n = (page-1)*limit;
        return n>0?n:0;
    }
}
