package cn.edu.nju.connector;

public interface Connector {
    public String getStates();
    public void updateStates(String url, String content);
    public void subscribeCallback(String content);
}