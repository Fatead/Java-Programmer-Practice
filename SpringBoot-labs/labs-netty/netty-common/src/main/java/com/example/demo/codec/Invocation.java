package com.example.demo.codec;

import com.alibaba.fastjson.JSON;
import com.example.demo.dispatcher.Message;

/**
 * 通信协议的消息体
 */
public class Invocation {

    /**
     * 类型
     */
    private String type;

    /**
     * JSON格式的消息
     */
    private String message;

    public Invocation(){

    }

    @Override
    public String toString() {
        return "Invocation{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public Invocation(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public Invocation(String type, Message message) {
        this.type = type;
        this.message = JSON.toJSONString(message);
    }

    public String getType() {
        return type;
    }

    public Invocation setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Invocation setMessage(String message) {
        this.message = message;
        return this;
    }

}
