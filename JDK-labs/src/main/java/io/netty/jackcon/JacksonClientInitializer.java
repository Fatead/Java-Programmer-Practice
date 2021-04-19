package io.netty.jackcon;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class JacksonClientInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new JacksonDecoder<JacksonBean>(JacksonBean.class));
        pipeline.addLast(new JacksonEncoder());
        pipeline.addLast(new JacksonClientHandler());
    }
}
