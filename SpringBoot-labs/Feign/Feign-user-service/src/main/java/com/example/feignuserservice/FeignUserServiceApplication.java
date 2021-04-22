package com.example.feignuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeignUserServiceApplication {

    public static void main(String[] args) {
        System.setProperty("server.port", "18080");
        SpringApplication.run(FeignUserServiceApplication.class, args);
    }

}
