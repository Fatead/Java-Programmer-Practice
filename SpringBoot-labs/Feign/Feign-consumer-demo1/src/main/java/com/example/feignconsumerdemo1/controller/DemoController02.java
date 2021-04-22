package com.example.feignconsumerdemo1.controller;

import com.example.feignconsumerdemo1.feign.UserServiceFeignClient02;
import com.example.feignconsumerdemo1.feign.request.UserAddRequest;
import com.example.feignconsumerdemo1.feign.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class DemoController02 {

    @Autowired
    private UserServiceFeignClient02 userServiceFeignClient;

    @GetMapping("/test01")
    public UserResponse test01() {
        return userServiceFeignClient.get(1);
    }

    @GetMapping("/test02A")
    public List<UserResponse> test02A() {
        return userServiceFeignClient.list("你猜", null);
    }


    @GetMapping("/test03")
    public Integer test03() {
        return userServiceFeignClient.add(new UserAddRequest()
                .setName("昵称").setGender(1));
    }

}
