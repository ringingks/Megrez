package com.Y3.AnalyticsTeam.CT.Yildun.Action;

import com.Y3.AnalyticsTeam.CT.consumer.CommonService;
import com.Y3.AnalyticsTeam.CT.consumer.ReportService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("rpt")
public class CtReportAction extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReportService rptSettingServ;
    @Autowired
    CommonService commServ;

    @GetMapping("/setting/accessing")
    public @ResponseBody String querySetting(HttpServletRequest request){
        LOGGER.info("### acction : CtReportAction.querySetting ###");
        int page = -1;
        int limit = -1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt( request.getParameter("page") );
        }
        if(request.getParameter("limit")!=null){
            limit = Integer.parseInt( request.getParameter("limit") );
        }
        return rptSettingServ.findReportAccessingSetting(limit,page);
    }

    @RequestMapping(value = "/setting/accessing", method = RequestMethod.POST)
    public @ResponseBody String updateSetting(HttpServletRequest request){
        LOGGER.info("### acction : CtReportAction.updateSetting ###");

        String requestBody = request.getParameter("data");
        String method = request.getParameter("method");

        if(method== null){
            return super.ResultsBuilder("0","parameters[method] missing",null,null);
        }else if(method.toUpperCase().equals("POST")) {
            return rptSettingServ.updateReportAccessingSetting(requestBody);
        }else if(method.toUpperCase().equals("PUT")){
            return rptSettingServ.addReportAccessingSetting(requestBody);
        }else if(method.toUpperCase().equals("DELETE")){
            return rptSettingServ.delReportAccessingSetting(requestBody);
        }else{
            return super.ResultsBuilder("0","no such request method",null,null);
        }
    }

    @RequestMapping(value = "/show/{rptid}", method = {RequestMethod.POST, RequestMethod.GET})
    public String showReportWithAuth(@PathVariable(value="rptid") int rptid, @RequestParam(value="user") int user,Model model){
        LOGGER.info("### " + rptSettingServ.findReportByUserid(user) );
        String rptInfo = rptSettingServ.findReportByUserid(user);
        JSONObject json = JSONObject.parseObject(rptInfo);
        json = json.getJSONArray("hits").getJSONObject(0);

        String ticketUser = json.getString("TICKETUSER");
        String ticket = JSONObject.parseObject( commServ.getReportServAuthTicket(ticketUser) ).getString("hits");

        String rptPath = json.getString("SERVERNAME")+"/trusted/"+ticket+"/"+json.getString("REPORTURL")+"?"+json.getString("URLPARAM");

        LOGGER.debug("json -> "+json);

        model.addAttribute("rptPath", rptPath);
        model.addAttribute("rptName", json.getString("REPORTNAME"));
        model.addAttribute("login_user", json.getString("LOGIN_USERNAME"));

        return "main";
    }

}
