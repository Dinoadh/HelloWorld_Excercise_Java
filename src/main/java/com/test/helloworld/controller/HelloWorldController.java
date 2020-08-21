package com.test.helloworld.controller;

import com.test.helloworld.model.HealthStatus;
import com.test.helloworld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller Class for Hello World App
 */
@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    /**
     * Rest Method to Return Hello! on default page
     */
    @GetMapping("/")
    public String sayHello() {
        return "Hello!";
    }

    /**
     * Rest Method to return  Health Status
     */
    @GetMapping("/healthz")
    public HealthStatus getHealthStatus() {
        return helloWorldService.getHealthStatus();
    }

}
