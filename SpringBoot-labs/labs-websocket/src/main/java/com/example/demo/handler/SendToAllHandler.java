package com.example.demo.handler;

import com.example.demo.message.Message;
import com.example.demo.message.SendToAllRequest;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest> {

    @Override
    public void execute(Session session, SendToAllRequest message) {

    }

    @Override
    public String getType() {
        return null;
    }

}
