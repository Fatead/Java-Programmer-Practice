package com.example.feignuserservice.controller;

import com.example.feignuserservice.request.UserAddRequest;
import com.example.feignuserservice.response.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")   //获得指定用户
    public UserResponse get(@RequestParam("id") Integer id){
        return new UserResponse().setId(id)
                .setName("昵称：" + id).setGender(id % 2 == 0 ? 1 : 2);
    }

    @GetMapping("/list")
    public List<UserResponse> list(@RequestParam(value = "name",required = false) String name,
                                   @RequestParam(value = "gender",required = false) Integer gender){
        List<UserResponse> users = new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            users.add(new UserResponse().setId(i)
                    .setName(name + "_" + i).setGender(gender));
        }
        return users;
    }

    @PostMapping("add") //添加用户
    public Integer add(@RequestBody UserAddRequest request){
        System.out.println("昵称：" + request.getName());
        System.out.println("性别：" + request.getGender());
        return 1;
    }
}
