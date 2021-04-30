package cn.edu.nju.model;

import java.util.List;

public class Config {
    private String host;
    private Security security;
    private List<Mapping> mapping;
    private List<Object> update;
    private Mqtt mqtt;

    public Config() {
    }

    public Config(String host, Security security, List<Mapping> mapping, List<Object> update, Mqtt mqtt) {
        this.host = host;
        this.security = security;
        this.mapping = mapping;
        this.update = update;
        this.mqtt = mqtt;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public List<Mapping> getMapping() {
        return mapping;
    }

    public void setMapping(List<Mapping> mapping) {
        this.mapping = mapping;
    }

    public List<Object> getUpdate() {
        return update;
    }

    public void setUpdate(List<Object> update) {
        this.update = update;
    }

    public Mqtt getMqtt() {
        return mqtt;
    }

    public void setMqtt(Mqtt mqtt) {
        this.mqtt = mqtt;
    }

    @Override
    public String toString() {
        return "Config [host=" + host + ", mapping=" + mapping + ", mqtt=" + mqtt + ", security=" + security
                + ", update=" + update + "]";
    }


}