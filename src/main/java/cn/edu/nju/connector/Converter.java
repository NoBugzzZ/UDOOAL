package cn.edu.nju.connector;


import com.alibaba.fastjson.JSONObject;

public interface Converter {
    
    public String convert(String content);

	JSONObject convert(String content, String config);
}