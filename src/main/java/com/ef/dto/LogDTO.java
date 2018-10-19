package com.ef.dto;

import org.joda.time.DateTime;

public class LogDTO {
    private DateTime date;
    private String ip;
    private String request;
    private short status;
    private String userAgent;

    public LogDTO() {
    }

    public LogDTO(DateTime date, String ip, String request, short status, String userAgent) {
        this.date = date;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "LogDTO{" +
                "date=" + date +
                ", ip='" + ip + '\'' +
                ", request='" + request + '\'' +
                ", status=" + status +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
