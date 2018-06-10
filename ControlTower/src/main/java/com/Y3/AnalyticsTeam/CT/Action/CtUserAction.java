package com.Y3.AnalyticsTeam.CT.Action;

import com.Y3.AnalyticsTeam.CT.BO.ReportBO;
import com.Y3.AnalyticsTeam.CT.BO.UserBO;
import com.Y3.AnalyticsTeam.CT.consumer.SystemService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("sys")
public class CtUserAction extends AbstractModule{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserBO userBO;
    @Autowired
    SystemService systemServ;
    @Autowired
    ReportBO rptBO;

    @RequestMapping(value = "/user/resetpwd", method = RequestMethod.POST)
    public @ResponseBody String resetUserPasswd(HttpServletRequest request) {
        LOGGER.info("### acction : resetPasswd ###");
        String uName = request.getParameter("username");
        String oPwd = request.getParameter("password");
        String nPwd = request.getParameter("newPassword");

        return userBO.pwdChange(uName, oPwd, nPwd).toJSONString();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody String queryUserInfo(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        return systemServ.findUserOnPage(page, limit);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public @ResponseBody String userUpdate(HttpServletRequest request) {
        String requestBody = request.getParameter("data");
        String method = request.getParameter("method");
        if (method == null) {
            return super.ResultsBuilder("0", "parameters[method] missing", null, null);
        } else if (method.toUpperCase().equals("POST")) {
            return systemServ.updateUser(requestBody);
        }else if(method.toUpperCase().equals("PUT")){
            return systemServ.addUser(requestBody);
        }else if(method.toUpperCase().equals("DELETE")){
            return systemServ.delUser(requestBody);
        }else{
            return super.ResultsBuilder("0","no such request method",null,null);
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public @ResponseBody String queryUserInfo(@RequestParam(value = "account") String account,@RequestParam(value = "password") String password){

        JSONObject loginCheck = userBO.loginCheck(account.toUpperCase(),password);

//        if(loginCheck.getString("state").equals("1")){
//            JSONObject hit = loginCheck.getJSONArray("hits").getJSONObject(0);
//
//            int userid = hit.getIntValue("userid");
//            rptBO.findReportByUserid( userid );
//
//            return "redirect:/rpt/show/user/"+userid;
//        }else{
//            return "/html/public/500";
//        }

        return loginCheck.toString();

    }


}
