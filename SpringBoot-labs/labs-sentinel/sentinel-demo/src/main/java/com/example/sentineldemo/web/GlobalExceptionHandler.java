package com.example.sentineldemo.web;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义sentinel-spring-webmvc-adapter提供的拦截器
 */
@ControllerAdvice(basePackages = "com.example.sentineldemo.controller")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BlockException.class)
    public String blockExceptionHandler(){
        return "请求过于频繁";
    }

}
