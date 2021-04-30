package cn.edu.nju.connector;

import java.io.IOException;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectorImpl implements Connector {
    private String host;
    private String authorization;
    private String contentType;
    private Converter downlinkConverter;
    private Converter uplinkConverter;
    private OkHttpClient client;
    private String getStatesUrl;
    private String downlinkConfig;

    public ConnectorImpl(String host, String authorization, String contentType, String getStatesUrl,
            String downlinkConfig) {
        this.host = host;
        this.authorization = authorization;
        this.contentType = contentType;
        downlinkConverter = new DownlinkConverter();
        uplinkConverter = new UplinkConverter();
        this.client = new OkHttpClient();
        this.getStatesUrl = getStatesUrl;
        this.downlinkConfig = downlinkConfig;
    }

    @Override
    public String getStates() {
        Request request = new Request.Builder().url(this.host + this.getStatesUrl)
                .addHeader("Authorization", this.authorization).addHeader("Content-Type", this.contentType).build();
        Response response;
        String result = "";
        try {
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uplinkConverter.convert(result);
    }

    @Override
    public void updateStates(String url, String content) {
        RequestBody requestBody = RequestBody.create(content, MediaType.parse("application/json"));
        Request request = new Request.Builder().url(this.host+url).addHeader("Authorization", this.authorization)
                .addHeader("Content-Type", this.contentType).post(requestBody).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribeCallback(String content) {
        JSONArray configs=JSON.parseArray(downlinkConfig);
        for (Iterator<Object> iterator = configs.iterator(); iterator.hasNext(); ) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject convertedContents = downlinkConverter.convert(content, next.toJSONString());
            String updateUrl=convertedContents.get("url").toString();
            String body=convertedContents.get("body").toString();
            System.out.println(updateUrl+"-----"+body);
            updateStates(updateUrl, body);
        }
    }

    @Override
    public String toString() {
        return "ConnectorImpl [authorization=" + authorization + ", contentType=" + contentType + ", downlinkConfig="
                + downlinkConfig + ", getStatesUrl=" + getStatesUrl + ", host=" + host + "]";
    }
}