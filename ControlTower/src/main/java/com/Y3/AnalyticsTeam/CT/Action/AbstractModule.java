package com.Y3.AnalyticsTeam.CT.Action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractModule {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected String ResultsBuilder(String state,String infos,Object hits,String callbacks){

        Map<String,Object> data = new LinkedHashMap<String, Object>();

        int count = 0;

        data.put("state", state);

        data.put("infos", infos);
        if(state.equals("0") && (infos==null || infos.equals("")) ){
            data.put("infos","Maybe Reason: an exception occurs when executing task!Please contact with your report administrator!");
        }

        if (hits == null){
            count = 0;
        }else if(hits instanceof List){
            count = ((List) hits).size();
        }else{
            count = 1;
        }

        data.put("count", count);
        data.put("hits", hits);

        String resultBack = JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);

        if (callbacks != null) {
            if (!callbacks.equals("")) {
                resultBack = callbacks + "(" + resultBack + ")";
            }
        }

        return resultBack;

    }

    protected String ResultsBuilder(String state,String infos,Object hits,String callbacks,int count){

        Map<String,Object> data = new LinkedHashMap<String, Object>();

        data.put("state", state);

        data.put("infos", infos);
        if(state.equals("0") && (infos==null || infos.equals("")) ){
            data.put("infos","Maybe Reason: an exception occurs when executing task!Please contact with your report administrator!");
        }

        if(count<0){
            data.put("count", 0);
        }else {
            data.put("count", count);
        }
        data.put("hits", hits);

        String resultBack = JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);

        if (callbacks != null) {
            if (!callbacks.equals("")) {
                resultBack = callbacks + "(" + resultBack + ")";
            }
        }

        return resultBack;

    }

}
