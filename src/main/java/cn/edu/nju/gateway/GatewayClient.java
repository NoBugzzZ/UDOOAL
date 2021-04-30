package cn.edu.nju.gateway;

import com.alibaba.fastjson.JSONObject;

import cn.edu.nju.connector.Connector;
import cn.edu.nju.connector.ConnectorImpl;
import cn.edu.nju.mqttclient.GatewayMqttClient;
import cn.edu.nju.mqttclient.MqttClientCallback;
import cn.edu.nju.utility.Utility;

public class GatewayClient {
    private String configFile;
    private String config;

    public GatewayClient(String configFile) {
        this.configFile = configFile;
    }
    public void start(){
        config=Utility.readConfig(configFile);
        JSONObject decodedConfig=Utility.decode(config);
        GatewayMqttClient gatewayMqttClient=new GatewayMqttClient(decodedConfig.get("mqttHost").toString(),
                        Integer.parseInt(decodedConfig.get("mqttPort").toString()),
                        decodedConfig.get("publishTopic").toString(),
                        decodedConfig.get("subscribeTopic").toString());
        Connector connector=new ConnectorImpl(decodedConfig.get("requestHost").toString(),
                        decodedConfig.get("authorization").toString(),
                        decodedConfig.get("contentType").toString(),
                        decodedConfig.get("getStatesUrl").toString(),
                        decodedConfig.get("downlinkConfig").toString());
        MqttClientCallback mqttClientCallback=new MqttClientCallback(connector);
        gatewayMqttClient.connect(mqttClientCallback);
        gatewayMqttClient.subscribe();
        while (true) {
            gatewayMqttClient.publish(connector.getStates());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}