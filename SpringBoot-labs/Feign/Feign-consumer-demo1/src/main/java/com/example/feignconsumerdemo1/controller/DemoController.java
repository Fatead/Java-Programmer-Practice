package com.example.feignconsumerdemo1.controller;

import com.example.feignconsumerdemo1.feign.UserServiceFeignClient;
import com.example.feignconsumerdemo1.feign.request.UserAddRequest;
import com.example.feignconsumerdemo1.feign.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @GetMapping("/test01")
    public UserResponse test01(){
        return userServiceFeignClient.get(1);
    }

    @GetMapping("/test02A")
    public List<UserResponse> test02A() {
        return userServiceFeignClient.list("你猜", 1);
    }

    @GetMapping("/test02B")
    public List<UserResponse> test02B() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", "昵称");
        return userServiceFeignClient.list(queryMap);
    }

    @GetMapping("/test03")
    public Integer test03() {
        return userServiceFeignClient.add(new UserAddRequest()
                .setName("昵称").setGender(1));
    }

}
