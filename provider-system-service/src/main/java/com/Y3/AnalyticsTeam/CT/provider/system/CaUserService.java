package com.Y3.AnalyticsTeam.CT.provider.system;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.ICaUserBO;
import com.Y3.AnalyticsTeam.CT.provider.system.DTO.UserDTO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CaUserPO;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/user")
public class CaUserService extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ICaUserBO userBO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String findUserOnPage(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return super.ResultsBuilder("1","ok",userBO.findUserOnPage(page,limit),null,userBO.count(-1));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute UserDTO user) {
        try{
            JSONObject r = new JSONObject();
            r.put("userid",userBO.updateUser(convert2User(user)));
            return super.ResultsBuilder("1","ok",r,null);
        }catch (Exception ex){
            LOGGER.error("### CaUserService.updateUser exception ###",ex);
            return super.ResultsBuilder("0","",null,null);
        }
    }

    @DeleteMapping(value = "{userid}")
    public String updateUser(@PathVariable(value="userid") String userid) {
        try{
            userBO.deleteUser(userid);
            return super.ResultsBuilder("1","ok",null,null);
        } catch (Exception ex){
            LOGGER.error("### CaUserService.updateUser exception ###",ex);
            return super.ResultsBuilder("0","Failed!",null,null);
        }

    }

    protected CaUserPO convert2User(UserDTO user){
        CaUserPO userPO = new CaUserPO();
        userPO.setUserid(user.getUserid());
        userPO.setCode(user.getUsercode());
        userPO.setName(user.getUsername());
        userPO.setManagerid(user.getManagerid());
        if(user.getUserpwd() != null && !user.getUserpwd().equals("")) {
            userPO.setPassword(user.getUserpwd());
        }
        userPO.setOrgid(user.getOrgid());
        userPO.setStatus(user.getStatus());

        return userPO;
    }

}
