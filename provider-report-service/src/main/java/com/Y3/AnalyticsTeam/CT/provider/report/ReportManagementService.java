package com.Y3.AnalyticsTeam.CT.provider.report;

import com.Y3.AnalyticsTeam.CT.provider.report.BO.IReportBO;
import com.Y3.AnalyticsTeam.CT.provider.report.PO.CtReportPO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rpt")
public class ReportManagementService extends AbstractModule{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IReportBO rptBO;

    @RequestMapping(value = "/setting/accessing/id/{_id}", method = RequestMethod.GET)
    public String findRptSetting(@PathVariable(value="_id") String _id){
       return super.ResultsBuilder("1","ok.",rptBO.findSettingOfAccessingById(_id),null);
    }

    @RequestMapping(value = "/setting/accessing", method = RequestMethod.GET)
    public String findRptSetting(@RequestParam("limit") int limit,@RequestParam("page") int page){
        return super.ResultsBuilder("1","ok.",rptBO.findSettingOfAccessingAll(page,limit),null,rptBO.countSettingOfAccessingAll());
    }

    @RequestMapping(value = "/setting/accessing", method = RequestMethod.POST)
    public String updateRptSetting(@RequestParam("data") String data){
        try {
            JSONObject json = JSONObject.parseObject(data);
            rptBO.updateSettingOfAccessing(convert2CtReportPO(json));
            return ResultsBuilder("1","ok.",null,null);
        }catch (Exception ex){
            LOGGER.error("### ReportManagementService.updateRptSetting exception ",ex);
            return ResultsBuilder("0",ex.getMessage(),null,null);
        }
    }

    @RequestMapping(value = "/setting/accessing", method = RequestMethod.PUT)
    public String addRptSetting(@RequestParam("data") String data){
        try {
            JSONObject json = JSONObject.parseObject(data);
            rptBO.addSettingOfAccessing(convert2CtReportPO(json));
            return ResultsBuilder("1","ok.",null,null);
        }catch (Exception ex){
            LOGGER.error("### ReportManagementService.addRptSetting exception ",ex);
            return ResultsBuilder("0",ex.getMessage(),null,null);
        }
    }

    @RequestMapping(value = "/setting/accessing", method = RequestMethod.DELETE)
    public String delRptSetting(@RequestParam("data") String data){
        try {
            List<Integer> arr = JSONArray.parseArray(data,int.class);

            rptBO.deleteSettingOfAccessingsByUIDs(arr);
            return ResultsBuilder("1","ok.",null,null);
        }catch (Exception ex){
            LOGGER.error("### ReportManagementService.addRptSetting exception ",ex);
            return ResultsBuilder("0",ex.getMessage(),null,null);
        }
    }


    @RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
    public String findRptByLoginUser(@PathVariable("userid") int userid){
        return super.ResultsBuilder("1","ok.",rptBO.findRptByUserid(userid),null);
    }

    private CtReportPO convert2CtReportPO(JSONObject json) {
        JSONObject menuObj = new JSONObject();
        CtReportPO ctReport = JSONObject.toJavaObject(json,CtReportPO.class);
        return ctReport;
    }


}
