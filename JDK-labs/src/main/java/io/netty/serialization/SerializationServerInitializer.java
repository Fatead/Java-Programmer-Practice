package io.netty.serialization;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * ChannelInitializer是一种特殊的ChannelInBoundHandler，它的实现类通常在
 * Bootstrap.handler() \ ServerBootstrap.handler() \ ServerBootstrap.childHandler() 上下文中使用
 * 用于构建channel的channelPipeline。
 *
 */
public class SerializationServerInitializer extends ChannelInitializer<Channel> {

    private final static int MAX_OBJECT_SIZE = 1024 * 1024;

    @Override
    protected void initChannel(Channel channel) throws Exception {
        //获取该channel对应的pipeLine
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new ObjectDecoder(MAX_OBJECT_SIZE,
                ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new SerializationServerHandler());
    }

}
