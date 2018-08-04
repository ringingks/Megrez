package com.Y3.AnalyticsTeam.CT.Cancer;

import com.Y3.AnalyticsTeam.CT.Cancer.DAO.RecApiDAO;
import com.Y3.AnalyticsTeam.CT.Cancer.DAO.SysRuleDAO;
import com.Y3.AnalyticsTeam.CT.Cancer.DTO.SysRuleMainSet;
import com.Y3.AnalyticsTeam.CT.Cancer.PO.CtRec4HLT;
import com.Y3.AnalyticsTeam.CT.Cancer.PO.CtRecApiResponse;
import com.Y3.AnalyticsTeam.CT.Cancer.consumer.SystemService;
import com.Y3.AnalyticsTeam.CT.Cancer.service.IS01AlertActService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class HPBLiveDataTask {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SystemService sysServ;

    @Autowired
    RecApiDAO dao;

    @Autowired
    IS01AlertActService s01AlertActServ;

    @Autowired
    SysRuleDAO sysRuleDAO;

    @Resource
    private ExecutorService executorService;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(initialDelayString="${jobs.initialDelay}", fixedRateString="${jobs.fixedRate}")
    public void executeFileDownLoadTask() {
        LOGGER.info("### Scheduled:"+format.format(new Date()));
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

        dao.insertRecApiResponse(recApiResponse); // 测试不写入数据库

        JSONArray arr = json.getJSONArray("hits");
        for(int i=0;i<arr.size();i++){
            try {
                JSONObject item = arr.getJSONObject(i);

                CtRec4HLT rec4HLT = convertJSONObject2CtRec4HLT(item,GUID);
//                rec4HLT.setUnit("TEST");
                // 多线程处理
                // 线程1 -> 写入数据库
                dao.insertRec4HLT( rec4HLT );
                // 线程1 -> 写入缓存，并且进行规则判断
                List<SysRuleMainSet> sysRuleMainSetsOnUse = sysRuleDAO.queryRuMainSetByRefID("01","1");

                // rule running
                if(!rec4HLT.getDevice_name().toUpperCase().equals("F1") && !rec4HLT.getDevice_name().toUpperCase().equals("F2") ) {
                    userThreadPool(rec4HLT, sysRuleMainSetsOnUse);
                }

            } catch (Exception e) {
                LOGGER.error("### "+e.toString(),e);
            }
        }

    }

    /***
     * JSONObject 转化为 CtRec4HLT 对象
     * @param item
     * @param GUID
     * @return
     */
    protected CtRec4HLT convertJSONObject2CtRec4HLT(JSONObject item,String GUID){

        if(GUID==null || GUID.equals("")){
            GUID = java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
        }

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

        return rec4HLT;
    }

    /***
     * 多线程处理处理
     */
    protected void userThreadPool(CtRec4HLT rec4HLT,List<SysRuleMainSet> sysRuleMainSetsOnUse){

        executorService.submit(new Runnable() {
            @Override
            public void run() {
//                ruleOperation(rec4HLT);
                s01AlertActServ.quertAlertText(rec4HLT,sysRuleMainSetsOnUse);
            }
        });
    }
}
