package cn.edu.nju.model;

public class HttpHeaders {
    private String accept;

    public HttpHeaders(String accept) {
        this.accept = accept;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    @Override
    public String toString() {
        return "HttpHeaders [accept=" + accept + "]";
    }

    public HttpHeaders() {
    }
    
}