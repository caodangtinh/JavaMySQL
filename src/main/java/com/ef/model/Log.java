package com.ef.model;

import com.ef.utils.Constant;
import org.joda.time.DateTime;

import java.util.Objects;

public class Log {
    private DateTime date;
    private String ip;
    private String request;
    private short status;
    private String userAgent;

    public Log() {

    }

    public Log(String line) {

    }

    public Log(String[] logLine) {
        this.setDate(Constant.DATE_TIME_FORMATTER.parseDateTime(logLine[0]));
        this.setIp(logLine[1]);
        // replace double quote from original value to avoid ""${value}""
        this.setRequest(Constant.DOUBLE_QUOTE.matcher(logLine[2]).replaceAll(""));
        this.setStatus(Short.valueOf(logLine[3]));
        // replace double quote from original value to avoid ""${value}""
        this.setUserAgent(Constant.DOUBLE_QUOTE.matcher(logLine[4]).replaceAll(""));
    }


    public Log(DateTime date, String ip, String request, short status, String userAgent) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return status == log.status &&
                Objects.equals(date, log.date) &&
                Objects.equals(ip, log.ip) &&
                Objects.equals(request, log.request) &&
                Objects.equals(userAgent, log.userAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, ip, request, status, userAgent);
    }

    @Override
    public String toString() {
        return "Log{" +
                "date=" + date +
                ", ip='" + ip + '\'' +
                ", request='" + request + '\'' +
                ", status=" + status +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
