package io.netty.jackcon;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JacksonClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        String jsonString = "";
        if (o instanceof JacksonBean) {
            JacksonBean user = (JacksonBean) o;
            jsonString = JacksonMapper.getInstance().writeValueAsString(user); // 对象转为json字符串
            System.out.println("Server -> Client: " + jsonString);
        }
    }
}
