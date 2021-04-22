package com.example.feignconsumerdemo1.config;

import com.example.feignconsumerdemo1.feign.UserServiceFeignClient;
import com.example.feignconsumerdemo1.feign.UserServiceFeignClient02;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.spring.SpringContract;
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

    @Bean
    public UserServiceFeignClient02 userServiceFeignClient02(){
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .contract(new SpringContract())   //使用SpringContract契约，契约负责解析API接口的方法元数据，例如注解、方法参数、方法返回类型等
                .target(UserServiceFeignClient02.class,"http://127.0.0.1:18080");  //目标地址
    }
}
