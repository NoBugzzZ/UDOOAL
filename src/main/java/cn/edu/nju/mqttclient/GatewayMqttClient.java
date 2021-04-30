package cn.edu.nju.mqttclient;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class GatewayMqttClient {
    private String host;
    private int port;
    private String publishTopic;
    private String subscribeTopic;
    private MqttClient mqttClient;

    public GatewayMqttClient(String host, int port, String publishTopic, String subscribeTopic) {
        this.host = host;
        this.port = port;
        this.publishTopic = publishTopic;
        this.subscribeTopic = subscribeTopic;
    }

    public void connect(MqttCallback callback) {
        try {
            this.mqttClient = new MqttClient(this.host + ":" + this.port, MqttClient.generateClientId());
            mqttClient.setCallback(callback);
            mqttClient.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String content){
        MqttMessage message=new MqttMessage();
        message.setPayload(content.getBytes());
        try {
            mqttClient.publish(publishTopic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(){
        try {
            mqttClient.subscribe(subscribeTopic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "GatewayMqttClient [host=" + host + ", port=" + port + ", publishTopic=" + publishTopic
                + ", subscribeTopic=" + subscribeTopic + "]";
    }
}