package io.netty.serialization;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SerializationServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if(o instanceof Bean){
            Bean bean = (Bean) o;
            channelHandlerContext.writeAndFlush(bean);
            System.out.println("Client -> Server: " + bean);
        }
    }
}
