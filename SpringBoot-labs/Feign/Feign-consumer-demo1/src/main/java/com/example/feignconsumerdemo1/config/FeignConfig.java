package com.example.feignconsumerdemo1.config;

import com.example.feignconsumerdemo1.feign.UserServiceFeignClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public UserServiceFeignClient userServiceFeignClient(){
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(UserServiceFeignClient.class,"http://127.0.0.1:18080");  //目标地址
    }
}
