package com.example.demo.handler;

import com.example.demo.message.SendToOneRequest;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
@Component
public class SendToOneHandler implements MessageHandler<SendToOneRequest> {

    @Override
    public void execute(Session session, SendToOneRequest message) {

    }

    @Override
    public String getType() {
        return null;
    }

}
