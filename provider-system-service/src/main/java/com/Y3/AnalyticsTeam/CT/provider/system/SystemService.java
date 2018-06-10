package com.Y3.AnalyticsTeam.CT.provider.system;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.IUserBO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtUserPO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sys")
public class SystemService extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IUserBO userBO;

    @RequestMapping(value = "/user/id/{_id}", method = RequestMethod.GET)
    public String findUserByID(@PathVariable(value="_id") String _id) {
        return super.ResultsBuilder("1","ok",userBO.findUserbyId(_id),null);
    }

    @RequestMapping(value = "/user/name/{_name}", method = RequestMethod.GET)
    public String findUserByName(@PathVariable(value="_name") String _name) {
        return super.ResultsBuilder("1","ok",userBO.findUserbyName(_name),null);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String findUserOnPage(@RequestParam("page") int page,@RequestParam("limit") int limit) {
        return super.ResultsBuilder("1","ok",userBO.findUserOnPage(page,limit),null,userBO.countUserTotal());
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String updateUser(@RequestParam("data") String data){
        try {
            JSONObject json = JSONObject.parseObject(data);
            CtUserPO userPO = convert2UserPO(json);
            userBO.updateUser(userPO);
            return ResultsBuilder("1","ok.",null,null);
        }catch (Exception ex){
            LOGGER.error("### UiMenuService.updateUser exception ",ex);
            return ResultsBuilder("0",ex.getMessage(),null,null);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String addUser(@RequestParam("data") String data){
        try {
            JSONObject json = JSONObject.parseObject(data);
            userBO.addUser(convert2UserPO(json));
            return ResultsBuilder("1","ok.",null,null);
        }catch (Exception ex){
            LOGGER.error("### UiMenuService.addUser exception ",ex);
            return ResultsBuilder("0","",null,null);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String delUser(@RequestParam("data") String data){
        List<Integer> arr = JSONArray.parseArray(data,int.class);
        userBO.deleteUserByIDs(arr);
        return ResultsBuilder("1","ok.",null,null);
    }

    private CtUserPO convert2UserPO(JSONObject json){
        JSONObject obj = new JSONObject();

        // userid
        if(json.getString("userid")!=null){
            obj.put("userid",json.getString("userid"));
        }
        // username
        if(json.getString("username")!=null){
            obj.put("username",json.getString("username"));
        }

        // upasswd
        if(json.getString("upasswd")!=null){
            obj.put("upasswd",json.getString("upasswd"));
        }else if(json.getString("userpwd")!=null){
            obj.put("upasswd",json.getString("userpwd"));
        }

        // orgid
        if(json.getString("orgid")!=null){
            obj.put("orgid",json.getString("orgid"));
        }

        // active_ind
        if(json.getString("active_ind")!=null){
            obj.put("active_ind",json.getString("active_ind"));
        }else if(json.getString("status")!=null){
            obj.put("active_ind",json.getString("status"));
        }

        return (CtUserPO)JSONObject.toJavaObject(obj, CtUserPO.class);
    }

}
