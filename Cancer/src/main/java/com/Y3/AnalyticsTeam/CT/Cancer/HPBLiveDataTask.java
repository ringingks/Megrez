package com.Y3.AnalyticsTeam.CT.Cancer;

import com.Y3.AnalyticsTeam.CT.Cancer.DAO.RecApiDAO;
import com.Y3.AnalyticsTeam.CT.Cancer.PO.CtRec4HLT;
import com.Y3.AnalyticsTeam.CT.Cancer.PO.CtRecApiResponse;
import com.Y3.AnalyticsTeam.CT.Cancer.consumer.SystemService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

//@Component
public class HPBLiveDataTask {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SystemService sysServ;

    @Autowired
    RecApiDAO dao;

    @Scheduled(initialDelayString="${jobs.initialDelay}", fixedRateString="${jobs.fixedRate}")
    public void executeFileDownLoadTask() {
        String GUID = java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();

        String responseStr = null;
        try {
            responseStr = sysServ.callAPIonGet("01");
        }catch (RetryableException ex){
            try{Thread.sleep(5000);} catch (InterruptedException e) {
            } finally {
                LOGGER.error("### RetryableException: feign request timeout! ###");
            }
            responseStr = sysServ.callAPIonGet("01");
        }
        JSONObject json = JSONObject.parseObject( responseStr );

        CtRecApiResponse recApiResponse = new CtRecApiResponse();
        recApiResponse.setRecid(GUID);
        recApiResponse.setResponse(json.getString("hits"));

        dao.insertRecApiResponse(recApiResponse);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONArray arr = json.getJSONArray("hits");
        for(int i=0;i<arr.size();i++){
            try {
                JSONObject item = arr.getJSONObject(i);
                CtRec4HLT rec4HLT = new CtRec4HLT();
                rec4HLT.setDevice_name(item.getString("name"));
                rec4HLT.setTemperature(item.getJSONArray("channels").getJSONObject(0).getBigDecimal("currentReading"));
                rec4HLT.setUnit(item.getJSONArray("channels").getJSONObject(0).getJSONObject("unit").getString("text"));
                rec4HLT.setDevice_batterylevel(item.getString("batteryLevel"));
                rec4HLT.setResponse_batch(GUID);
                rec4HLT.setLocationguid(item.getString("locationGUID"));

                String createtime = item.getJSONObject("currentReadings").getString("datetime");
                createtime = createtime.substring(createtime.indexOf("(")+1,createtime.lastIndexOf(")"));
                createtime = createtime.substring(0,createtime.indexOf("+"));
                rec4HLT.setCreatetime( format.format( Long.parseLong(createtime) ) );

                dao.insertRec4HLT(rec4HLT);
            } catch (Exception e) {
                LOGGER.error("### ",e);
            }



        }



    }
}
