package cn.edu.nju.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Utility {
    public static String decode(MqttMessage message) {
        byte[] b = message.getPayload();
        String result="";
        try {
            result = new String(b, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String readConfig(String path) {
        String result = "";
        File f = new File(path);
        InputStream in;
        try {
            in = new FileInputStream(f);
            byte[] b = new byte[(int) f.length()];
            in.read(b);
            in.close();
            // System.out.println(new String(b));
            result = new String(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject decode(String config){
        JSONObject result=new JSONObject();
        JSONObject root=JSON.parseObject(config);
        result.put("requestHost",root.get("host"));
        result.put("authorization", JSON.parseObject(root.get("security").toString()).get("token"));
        JSONObject mapping = JSON.parseObject(JSON.parseArray(root.get("mapping").toString()).get(0).toString());
        result.put("contentType", JSON.parseObject(mapping.get("httpHeaders").toString()).get("accept").toString());
        result.put("getStatesUrl", mapping.get("url").toString());
        result.put("downlinkConfig", root.get("update").toString());
        result.put("uplinkConfig", mapping.get("converter").toString());
        JSONObject mqtt=JSON.parseObject(root.get("mqtt").toString());
        result.put("mqttHost", mqtt.get("host").toString());
        result.put("mqttPort", mqtt.get("port"));
        result.put("publishTopic", mqtt.get("publishTopic"));
        result.put("subscribeTopic", mqtt.get("subscribeTopic"));
        return result;
    }
}