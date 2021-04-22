package com.example.sentineldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinelDemoApplication {

    public static void main(String[] args) {
        System.setProperty("project.name","demo-application");
        SpringApplication.run(SentinelDemoApplication.class, args);
    }

}
