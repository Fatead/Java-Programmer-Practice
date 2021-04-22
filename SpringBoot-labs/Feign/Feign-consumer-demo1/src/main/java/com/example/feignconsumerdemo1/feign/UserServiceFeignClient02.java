package com.example.feignconsumerdemo1.feign;

import com.example.feignconsumerdemo1.feign.request.UserAddRequest;
import com.example.feignconsumerdemo1.feign.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @RequstParam 注解可选参数，允许传入null值，所以我们无需使用@QueryMap方法注解
 */
public interface UserServiceFeignClient02 {

    @GetMapping("/user/get")
    UserResponse get(@RequestParam("id") Integer id);

    @GetMapping("/user/list")
    List<UserResponse> list(@RequestParam("name") String name,
                            @RequestParam("gender") Integer gender);

    @PostMapping(value = "/user/get",consumes = "application/json")
    Integer add(@RequestBody UserAddRequest request);

}
