package cn.edu.nju.connector;

import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DownlinkConverter implements Converter {

    @Override
    public String convert(String content) {
        return "null";
    }

    @Override
    public String convert(String content, String config) {
        JSONObject result=new JSONObject();
        JSONObject bodyConfig=new JSONObject();
        JSONObject configJson=JSON.parseObject(config);
        JSONObject states=JSON.parseObject(JSON.parseObject(content).get("content").toString());
        if(configJson.get("type").toString().equals("1")){
            JSONArray details=JSON.parseArray(configJson.get("details").toString());
            for (Iterator<Object> iterator = details.iterator(); iterator.hasNext(); ) {
                JSONObject detail = (JSONObject) iterator.next();
                if(detail.get("value").toString().equals(states.get(configJson.get("key").toString()))){
                    result.put("url", detail.get("url"));
                    bodyConfig=JSON.parseObject(detail.get("body").toString());
                    break;
                }
            }
        }else if(configJson.get("type").toString().equals(new String("2").toString())){
            bodyConfig=JSON.parseObject(configJson.get("body").toString());
            result.put("url", configJson.get("url"));
        }
        JSONObject body=new JSONObject();
        for (Map.Entry<String, Object> entry : bodyConfig.entrySet()) {
            body.put(entry.getKey().toString(), states.get(entry.getValue().toString()));
        }
        result.put("body", body.toJSONString());
        return result.toJSONString();
    }
}