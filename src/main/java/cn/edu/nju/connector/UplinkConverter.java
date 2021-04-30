package cn.edu.nju.connector;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UplinkConverter implements Converter {

    @Override
    public String convert(String content) {
        JSONObject states=JSON.parseObject(content);
        JSONObject attributes= (JSONObject) states.get("attributes");
        JSONObject contentJson= new JSONObject();
        contentJson.put("Name", states.get("entity_id"));
        contentJson.put("Brand","xiaomi");
        contentJson.put("State",states.get("state"));
        contentJson.put("Speed", attributes.get("mode"));
        contentJson.put("Humidity",attributes.get("humidity"));
        BigDecimal bigDecimal= (BigDecimal)attributes.get("temperature");
        contentJson.put("Temperature",(int)Double.parseDouble(bigDecimal.toString()));
        contentJson.put("Aqi",attributes.get("aqi"));
        JSONObject result=new JSONObject();
        result.put("udoi","123");
        result.put("schemaId","Air_purifier");
        result.put("content",contentJson);
        return result.toJSONString();
    }

    @Override
    public JSONObject convert(String content, String config) {
        return null;
    }
}