package io.netty.serialization;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SerializationClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if(o instanceof Bean){
            Bean bean = (Bean) o;
            System.out.println("Server -> Client: " + bean);
        }
    }
}
