package com.example.demo.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.message.Message;
import com.example.demo.util.WebSocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@ServerEndpoint("/")
public class WebSocketServerEndpoint implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 消息类型和MessageHandler的映射
    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * websocket连接建立时会调用这个函数onOpen()
     */
    @OnOpen
    public void onOpen(){

    }

    /**
     * websocket连接关闭时会调用这个函数onClose()
     */
    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        logger.info("[onClose][session({}) 连接关闭。关闭原因是({})}]", session, closeReason);
        WebSocketUtil.removeSession(session);
    }


    /**
     * 收到消息时调用的函数
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session,String message){
        logger.info("[onOpen][session({})] 接收到一条消息({})",session,message);
        try {
            JSONObject jsonObject = JSON.parseObject(message);
            //获取消息的类型
            String messageType = jsonObject.getString("type");
            //从map中获取这种消息对应的handler
            MessageHandler messageHandler = HANDLERS.get(messageType);
            if(messageHandler == null){
                logger.error("[onMessage][消息类型({}) 不存在消息处理器]", messageType);
                return;
            }
            Class<? extends Message> messageClass;
        }catch (Throwable throwable){
            logger.error("[onMessage][session({})] message({}) 发生异常",session,message);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("[onClose][session({}) 发生异常]", session, throwable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }


    private Class<? extends Message> getMessageClass(MessageHandler handler){
        /*
         *获得Bean对应的Class类名，因为有可能被AOP代理过。
         * ultimateTargetClass(Object candidate) 获取一个代理对象的最终类型。
         * 例如如果获取到的目标对象是一个CGLIB代理对象，那么获取其父类类型
         */
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        // 获得接口的 Type 数组
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)) { // 此处，是以父类的接口为准
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)) {
            // 遍历 interfaces 数组
            for (Type type : interfaces) {
                // 要求 type 是泛型参数
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler 接口
                    if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class<Message>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
    }


}
