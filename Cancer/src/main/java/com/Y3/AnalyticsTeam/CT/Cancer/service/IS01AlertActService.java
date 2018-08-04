package com.Y3.AnalyticsTeam.CT.Cancer.service;

import com.Y3.AnalyticsTeam.CT.Cancer.DTO.SysRuleMainSet;
import com.Y3.AnalyticsTeam.CT.Cancer.PO.CtRec4HLT;

import java.util.List;

public interface IS01AlertActService {

    void quertAlertText(CtRec4HLT rec4HLT,List<SysRuleMainSet> sysRuleMainSetsOnUse);

}
