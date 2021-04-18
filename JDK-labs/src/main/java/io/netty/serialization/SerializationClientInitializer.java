package io.netty.serialization;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class SerializationClientInitializer extends ChannelInitializer<Channel> {

    private final static int MAX_OBJECT_SIZE = 1024 * 1024;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ObjectDecoder(MAX_OBJECT_SIZE,
                ClassResolvers.weakCachingConcurrentResolver(this.getClass()
                        .getClassLoader())));
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new SerializationClientHandler());
    }

}
