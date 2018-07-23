package com.Y3.AnalyticsTeam.CT.Cancer.DAO;

import com.Y3.AnalyticsTeam.CT.Cancer.PO.CtRec4HLT;
import com.Y3.AnalyticsTeam.CT.Cancer.PO.CtRecApiResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecApiDAO {

    void insertRecApiResponse(CtRecApiResponse recApiResponse);

    void insertRec4HLT(CtRec4HLT rec4HLT);
}
