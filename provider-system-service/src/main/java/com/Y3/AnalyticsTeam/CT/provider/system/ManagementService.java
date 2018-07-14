package com.Y3.AnalyticsTeam.CT.provider.system;

import com.Y3.AnalyticsTeam.CT.provider.system.BO.IManagementBO;
import com.Y3.AnalyticsTeam.CT.provider.system.PO.CtApiPO;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("mng")
public class ManagementService extends AbstractModule {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IManagementBO mbo;

    @RequestMapping(value = "/api/call/{_code}", method = RequestMethod.GET)
    public String callAPIonGet(@PathVariable(value="_code") String _code){
        try {
            return super.ResultsBuilder("1","ok",mbo.callAPI(_code),null);
        } catch (Exception e) {
            LOGGER.error("### ",e);
            return super.ResultsBuilder("0",e.getMessage(),null,null);
        }
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String findAPI(@RequestParam("page") int page,@RequestParam("limit") int limit){
        try {
            return super.ResultsBuilder("1","ok",mbo.findApiList(page,limit),null,mbo.apiListCountTotal());
        } catch (Exception e) {
            LOGGER.error("### ",e);
            return super.ResultsBuilder("0",e.getMessage(),null,null);
        }
    }

    @RequestMapping(value = "/api/params/{apiID}", method = RequestMethod.GET)
    public String findAPIparams(@PathVariable(value="apiID") String apiID){
        try {
            return super.ResultsBuilder("1","ok",mbo.findApiParamsList(apiID),null);
        } catch (Exception e) {
            LOGGER.error("### ",e);
            return super.ResultsBuilder("0",e.getMessage(),null,null);
        }
    }

    @RequestMapping(value = "/api", method = RequestMethod.POST)
    public String updateApi(@RequestParam("data") String data){
        try {
            JSONObject json = JSONObject.parseObject(data);
            JSONObject json_new = new JSONObject();
            for(String key : json.keySet()){
                if(json.get(key)!=null && !json.equals(""))
                    json_new.put(key,json.get(key));
            }
            LOGGER.info("### "+json_new);
            if(json_new.getString("uuid")==null || json_new.getString("uuid").equals("")){

            }
            CtApiPO apiPO = (CtApiPO)JSONObject.toJavaObject(json_new, CtApiPO.class);
            int r_ = mbo.updateApi(apiPO);
            if(r_<1){
                return ResultsBuilder("0","no records updated.",null,null);
            }
            return ResultsBuilder("1","ok.",null,null);
        }catch (Exception ex){
            LOGGER.error("### ManagementService.updateApi exception ",ex);
            return ResultsBuilder("0",ex.getMessage(),null,null);
        }
    }

    @DeleteMapping(value = "/api/{apiID}")
    public String deleteAPI(@PathVariable(value="apiID") String apiID){
        try {
            mbo.deleteAPI(apiID);
            return super.ResultsBuilder("1","ok",null,null);
        } catch (Exception e) {
            LOGGER.error("### ",e);
            return super.ResultsBuilder("0",e.getMessage(),null,null);
        }
    }

}
