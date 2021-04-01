package com.example.demo.handler;

import com.example.demo.message.AuthRequest;
import com.example.demo.message.AuthResponse;
import com.example.demo.message.UserJoinNoticeRequest;
import com.example.demo.util.WebSocketUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * 用户认证请求的消息处理类
 */
@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest>{

    /**
     * 认证某个用户，如果认证通过，则广播通知给所有人
     * @param session 会话
     * @param message 消息
     */
    @Override
    public void execute(Session session, AuthRequest message) {
        if(null == message.getAccessToken() || Strings.isEmpty(message.getAccessToken())){
            WebSocketUtil.send(session,AuthRequest.TYPE,
                    new AuthResponse().setCode(1).setMessage("认证 accessToken 未传入"));
            return;
        }
        WebSocketUtil.addSession(session,message.getAccessToken());
        WebSocketUtil.send(session,AuthResponse.TYPE,new AuthResponse().setCode(0));
        WebSocketUtil.broadcast(UserJoinNoticeRequest.TYPE,
                new UserJoinNoticeRequest().setNickname(message.getAccessToken()));

    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }

}
