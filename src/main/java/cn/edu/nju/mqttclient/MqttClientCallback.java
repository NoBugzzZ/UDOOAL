package cn.edu.nju.mqttclient;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import cn.edu.nju.connector.Connector;
import cn.edu.nju.utility.Utility;

public class MqttClientCallback implements MqttCallback {
    Connector connector;
    public MqttClientCallback(Connector connector){
        this.connector = connector;
    }
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        String decodedMessage = Utility.decode(message);
        connector.subscribeCallback(decodedMessage);
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("connectionLost:"+cause.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        //System.out.println("deliveryComplete:" + token.toString());
    }
}