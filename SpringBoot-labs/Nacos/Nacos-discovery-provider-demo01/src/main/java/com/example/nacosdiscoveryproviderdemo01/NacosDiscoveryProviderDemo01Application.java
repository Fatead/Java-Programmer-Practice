package com.example.nacosdiscoveryproviderdemo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报错：Spring Boot ClassNotFoundException org.springframework.core.metrics.ApplicationStartup
 * 一般是因为子项目的版本高于父项目
 */
@SpringBootApplication
@EnableDiscoveryClient  //开启Spring Cloud的注册发现功能
public class NacosDiscoveryProviderDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryProviderDemo01Application.class, args);
    }

    @RestController
    static class TestController{

        @GetMapping("/echo")
        public String echo(String name) {
            return "provider:" + name;
        }

    }

}
