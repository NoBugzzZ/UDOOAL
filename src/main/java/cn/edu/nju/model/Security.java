package cn.edu.nju.model;

public class Security {
    private String type;
    private String token;

    public Security(String type, String token) {
        this.type = type;
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Security [token=" + token + ", type=" + type + "]";
    }

    public Security() {
    }
}