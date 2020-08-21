package com.test.helloworld.service;

import com.test.helloworld.model.HealthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Service class of Hello World to return Health Status
 */
@Service
public class HelloWorldService {

    @Autowired
    private BuildProperties buildProperties;


    public HealthStatus getHealthStatus() {
        //RuntimeMXBean in java provides the runtime instance and the time when it started.
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        //DateFormatter
        String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").withZone(ZoneId.systemDefault())
                .format(Instant.ofEpochMilli(rb.getStartTime()));
        return new HealthStatus(HttpStatus.OK.name(), buildProperties.getVersion(), "up since " + startTime);
    }
}
