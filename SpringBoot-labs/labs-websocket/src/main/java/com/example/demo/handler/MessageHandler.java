package com.example.demo.handler;

import com.example.demo.message.Message;

import javax.websocket.Session;

/**
 * 消息处理器接口
 */
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     * @param session 会话
     * @param message 消息
     */
    void execute(Session session, T message);

    /**
     * @return 消息类型
     */
    String getType();

}
