package com.Y3.AnalyticsTeam.CT.Cancer.service.Impl;

import com.Y3.AnalyticsTeam.CT.Cancer.DAO.S01AlertActionDAO;
import com.Y3.AnalyticsTeam.CT.Cancer.DAO.SysRuleDAO;
import com.Y3.AnalyticsTeam.CT.Cancer.DTO.AlertTextDTO;
import com.Y3.AnalyticsTeam.CT.Cancer.DTO.SysRuleItemSet;
import com.Y3.AnalyticsTeam.CT.Cancer.DTO.SysRuleMainSet;
import com.Y3.AnalyticsTeam.CT.Cancer.PO.CtRec4HLT;
import com.Y3.AnalyticsTeam.CT.Cancer.service.IS01AlertActService;
import com.Y3.AnalyticsTeam.CT.Cancer.service.MyRedisService;
import com.Y3.AnalyticsTeam.CT.util.HttpHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

@Service
public class S01AlertActServiceImpl implements IS01AlertActService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    S01AlertActionDAO alertDao;

    @Autowired
    SysRuleDAO sysRuleDAO;

    @Autowired
    private MyRedisService myRedisService;

    @Resource
    private ExecutorService executorService;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void quertAlertText(CtRec4HLT rec4HLT, List<SysRuleMainSet> sysRuleMainSetsOnUse) {
//        List<SysRuleMainSet> sysRuleMainSetsOnUse = sysRuleDAO.queryRuMainSetByRefID("01","1");
        for(SysRuleMainSet ruMainSet : sysRuleMainSetsOnUse){
            boolean flag = false;
            String mainID = ruMainSet.getSuid();
            List<SysRuleItemSet> ruleItemSets = sysRuleDAO.queryRuItemByMainid(mainID);
            SysRuleItemSet ruleItemSet = null;
            String setting_temperature = "";
            for( int i = 0;i<ruleItemSets.size();i++ ){
                ruleItemSet = ruleItemSets.get(i);
                String elementcode = ruleItemSet.getElementcode();
                String value_str = ruleItemSet.getValueset();
                BigDecimal value_num = null;
                int expression = ruleItemSet.getExpression();
                int isNum = ruleItemSet.getIsnum();
                int r = -2;
                // 1 : '<',小于 ; 2 : '>',大于 ; 3 : '<=',小于等于 ; 4 : '>=',大于等于 ; 5 : '=',等于
                if(isNum==1){
                    value_num = new BigDecimal(value_str);
                    r = rec4HLT.getTemperature().compareTo(value_num);

                    switch(r){
                        case -1:
                            if(expression==1 || expression==3) {flag = true; setting_temperature = "<"+value_str;}
                            break;
                        case 0:
                            if(expression==3 || expression==4 || expression==5 ) {flag = true; setting_temperature = "="+value_str;}
                            break;
                        case 1:
                            if(expression==2 || expression==4 ) {flag = true; setting_temperature = ">"+value_str;}
                            break;
                        default: flag = false;
                            break;
                    }

                }

                if(flag){
                    break;
                }
            }

            if(ruleItemSet==null || !flag){
                continue;
            }

            LOGGER.debug("### warning temperature->"+rec4HLT.getTemperature()+"@"+rec4HLT.getDevice_name());

            String groupid = ruMainSet.getGroupid();
            String device = rec4HLT.getDevice_name();

            String cache_key = "[S01]"+device+"@"+groupid; // 定义key为 device@alert_group_id
            int diffSet = ruMainSet.getFrequency(); // 间隔时长
            long secsDiff = diffSet; //默认为符合预警操作的时长
            // 读取缓存数据
            String cache_value = myRedisService.getString(cache_key);
            LocalDateTime currentTime = LocalDateTime.now();
            long lasttime = 0;
            String newValues = dateTimeFormatter.format(currentTime);
            // 检查时间经过
            if(cache_value != null) {
                String[] caches = cache_value.split(",");
                LocalDateTime lastActionTime = LocalDateTime.parse(caches[0],dateTimeFormatter);
                secsDiff = ChronoUnit.SECONDS.between(lastActionTime,currentTime);

                if(caches.length>1){
                    LocalDateTime firstActionTime = LocalDateTime.parse(caches[1],dateTimeFormatter);
                    lasttime = ChronoUnit.SECONDS.between(firstActionTime,currentTime);
                    newValues = newValues+","+caches[1];
                }else{
                    newValues = newValues+","+newValues;
                }
            }


            if(secsDiff<diffSet || lasttime>1800){ //如果间隔时长未达到预定设置，则不进行预警
                continue;
            }

            LOGGER.debug("### currentTime -> "+dateTimeFormatter.format(currentTime)+" ###");
            LOGGER.debug("### DEVICE["+rec4HLT.getDevice_name()+"]diffSet:"+diffSet+" ,secsDiff:"+secsDiff+" ###");

            myRedisService.setString(cache_key, newValues); // 更新缓存数据

            List<AlertTextDTO> alertTexts = alertDao.queryAlertText(groupid);


            for(AlertTextDTO alertText : alertTexts){
                try {
                    String subject = alertText.getSubject();
                    subject = subject.replace("[device]",device);

                    String text = alertText.getText();
                    text = text.replace("[device]",device);
                    text = text.replace("[createtime]",rec4HLT.getCreatetime());
                    text = text.replace("[temperature]",rec4HLT.getTemperature().toString());
                    text = text.replace("[setting_temperature]",setting_temperature);

                    String finalText = text;
                    String finalSubject = subject;

                    sendSMS(alertText.getTel(), finalText);

                    sendmail(alertText.getEmail(), finalSubject, finalText);

                } catch (Exception e) {
                    LOGGER.error("### failed to send message to "+alertText.getKeeper()+" -> "+e.toString()+" ###",e);
                }
            }

        }
    }

    /***
     * 发送邮件
     * @param to
     * @param subject
     * @param html
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    protected void sendmail(String to,String subject,String html) throws MessagingException, UnsupportedEncodingException {

        LOGGER.info("### mail->"+to+" ###" );

        if(to==null || to.equals("")){
            return;
        }

        String HOST = "appsmail2.ychsystems.com";
        Integer PORT = 25;
        String USERNAME = "hpbalert";
        String PASSWORD = "#9b@l#rT";
        String EMAILFORM = "hpbalert@YCH.COM";
        String encoding = "UTF-8";

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(HOST);
        mailSender.setPort(PORT);
        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASSWORD);
        mailSender.setDefaultEncoding(encoding);
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.transport.protocol", "smtp");
        p.put("mail.smtp.starttls.enable", "false");
        p.put("mail.smtp.auth", "true");
        mailSender.setJavaMailProperties(p);


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, encoding);
        messageHelper.setFrom(EMAILFORM, "HBP-ALERT");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    /***
     * 发送短消息
     */
    protected void sendSMS(String tel,String content){
        String to = tel.replace("-","").replace(" ","").replace("+","");
        String sms_api_ = "http://sms.y3technologies.com:1401/send?username=YCH-HPB&password=Y3h@H93&from=YCH-ALERTS&to=#to#&content=#content#";
        content = content.toUpperCase().replace("<BR/>","\n").replace("<B>","").replace("</B>","");

        LOGGER.info("### send SMS->"+to+" ###");

        if(to==null || to.equals("")){
            return;
        }

        try {
            sms_api_ = sms_api_.replace("#to#",to).replace("#content#",URLEncoder.encode(content,"utf-8"));
            HttpHelp.doGet(sms_api_,"utf-8");
        } catch (Exception e) {
            LOGGER.error("### failed to send to "+tel+" SMS -> "+e.toString()+" ###",e);
        }
    }

    protected void recActLog(){

    }
}
