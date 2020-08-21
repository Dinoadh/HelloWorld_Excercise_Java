package com.test.helloworld.model;

/**
 * POJO class for HealthStatus
 */
public class HealthStatus {

    private final String status;

    private final String version;

    private final String uptime;

    public HealthStatus(String status, String version, String uptime) {
        this.status = status;
        this.version = version;
        this.uptime = uptime;
    }

    public String getStatus() {
        return status;
    }


    public String getVersion() {
        return version;
    }


    public String getUptime() {
        return uptime;
    }

}
