package io.netty.jackcon;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JacksonServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("client:" + incoming.remoteAddress() + "异常");
        cause.printStackTrace();
        //当出现异常时关闭连接
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        String jsonString = "";
        if(o instanceof JacksonBean){
            JacksonBean bean = (JacksonBean) o;
            channelHandlerContext.writeAndFlush(bean);
            jsonString = JacksonMapper.getInstance().writeValueAsString(bean);
            System.out.println("client -> server" + jsonString);
        }
    }

}
