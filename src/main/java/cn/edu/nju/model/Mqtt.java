package cn.edu.nju.model;

public class Mqtt {
    private String host;
    private int port;
    private String publishTopic;
    private String subscribeTopic;

    public Mqtt(String host, int port, String publishTopic, String subscribeTopic) {
        this.host = host;
        this.port = port;
        this.publishTopic = publishTopic;
        this.subscribeTopic = subscribeTopic;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPublishTopic() {
        return publishTopic;
    }

    public void setPublishTopic(String publishTopic) {
        this.publishTopic = publishTopic;
    }

    public String getSubscribeTopic() {
        return subscribeTopic;
    }

    public void setSubscribeTopic(String subscribeTopic) {
        this.subscribeTopic = subscribeTopic;
    }

    @Override
    public String toString() {
        return "Mqtt [host=" + host + ", port=" + port + ", publishTopic=" + publishTopic + ", subscribeTopic="
                + subscribeTopic + "]";
    }

    public Mqtt() {
    }
    
}