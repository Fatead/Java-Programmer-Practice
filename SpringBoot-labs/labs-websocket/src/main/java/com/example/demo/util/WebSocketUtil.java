package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 提供客户端连接的管理等功能
 * websocket session发送文本消息有两个方法，getAsyncRemote()和getBasicRemote()两个方法
 * 其中getAsyncRemote()是异步非阻塞的.
 * HTML5 WebSocket设计出来的目的就是使客户端浏览器具备像C/S框架下桌面系统的即时通讯能力，
 * 实现了浏览器和服务器的全双工通信。
 * 优点：双向通信、事件驱动、异步、使客户端能够实现真正意义上的推送功能
 *
 * 使用实例（场景）：社交聊天、弹幕、多玩家玩游戏、协同编辑、股票基金实时报价、视频会议、基于位置的应用
 */
public class WebSocketUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketUtil.class);

    //session到用户的映射
    private static final Map<Session,String> sessionUserMap = new ConcurrentHashMap<>();

    //用户到session的映射
    private static final Map<String,Session> userSessionMap = new ConcurrentHashMap<>();

    /**
     * 在两个map中添加用户和session的映射
     */
    public static void addSession(Session session,String user){
        sessionUserMap.put(session,user);
        userSessionMap.put(user,session);
    }

    public static void removeSession(Session session){
        String user = sessionUserMap.remove(session);
        if(user!= null && userSessionMap.size()>0){
            userSessionMap.remove(user);
        }
    }

    /**
     * 基于Session.getBasicRemote()发送消息
     */
    private static void sendTextMessage(Session session,String messageText){
        if (session == null) {
            LOGGER.error("[sendTextMessage][session为null]");
            return;
        }
        RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            LOGGER.error("[sendTextMessage][session的basic为null]");
            return;
        }
        try {
            basic.sendText(messageText);
        }catch (IOException e){
            LOGGER.error("[sendTextMessage][session({}) 发送消息{}) 发生异常",
                    session, messageText, e);
        }
    }


    /**
     * 根据消息类型和消息内容构建完整的JSON格式的消息，并转换为字符串
     * @param type
     * @param message
     * @param <T>
     * @return
     */
    private static <T extends Message> String buildTextMessage(String type,T message){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("message",message);
        return jsonObject.toJSONString();
    }


    /**
     * 广播发送消息给所有的用户
     * 遍历map中存的所有session,给所有的session发送消息
     * @param type
     * @param message
     * @param <T>
     */
    public static <T extends Message> void broadcast(String type, T message){
        String messageText = buildTextMessage(type,message);
        for(Session session:sessionUserMap.keySet()){
            sendTextMessage(session,messageText);
        }
    }

    /**
     * 根据session发送消息给单个用户
     * @param session
     * @param type
     * @param message
     * @param <T>
     */
    public static <T extends Message> void send(Session session,String type, T message){
        String messageText = buildTextMessage(type,message);
        sendTextMessage(session,messageText);
    }


    public static <T extends Message> boolean send(String user,String type, T message){
        Session session = userSessionMap.get(user);
        if(null == session) {
            LOGGER.error("[send][user({}) 不存在对应的 session]", user);
            return false;
        }
        send(session,type,message);
        return true;
    }

}
