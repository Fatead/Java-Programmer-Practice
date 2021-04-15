package com.example.demo.dispatcher;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.aopalliance.intercept.Invocation;

public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Invocation invocation) throws Exception {

    }

}
